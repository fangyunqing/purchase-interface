package com.hf.project.cloth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.cloth.check.billcode.CheckDeletePoBillService;
import com.hf.project.cloth.check.record.CheckSupplierService;
import com.hf.project.cloth.entity.MRPRequest;
import com.hf.project.cloth.entity.OrderInfo;
import com.hf.project.cloth.entity.Supplier;
import com.hf.project.cloth.entity.po.PoBillDetail;
import com.hf.project.cloth.entity.po.PoBillMaster;
import com.hf.project.cloth.mapper.ClothUtilMapper;
import com.hf.project.cloth.mapper.PoBillMapper;
import com.hf.project.common.check.billcode.CheckExistRequestService;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.check.record.CheckUserService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.mapper.common.MaterialMapper;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.common.service.datadict.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 订单服务类
 * @Author fyq
 * @Date 2021/9/8 11:05
 **/

@Service
public class PoBillService implements PurchaseOrderService<PoBillMaster> {

    @Autowired
    private PoBillMapper poBillMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private ClothUtilMapper clothUtilMapper;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckUserService checkUserService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckExistRequestService checkRequestService;

    @Autowired
    private CheckDeletePoBillService checkPoBillService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<PoBillMaster> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(purchasePlanHdr -> {
            if (poBillMapper.existBillCode(purchasePlanHdr.getPlanNo())) {
                existBillCodes.add(purchasePlanHdr.getPlanNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ORDER.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        // 计算
        Integer maxDetailId = poBillMapper.getMaxDetailId();
        Integer maxMasterId = poBillMapper.getMaxMasterId();
        Integer maxInvoiceId = clothUtilMapper.getMaxInvoiceId();
        Integer maxPaymentId = clothUtilMapper.getMaxPaymentId();
        Integer maxUnitId = clothUtilMapper.getMaxUnitId();
        Integer maxCurrencyId = clothUtilMapper.getMaxCurrencyId();

        for (PoBillMaster poBillMaster : list) {

            // 首先判断明细条数是否为空
            if (poBillMaster.getPoBillDetails().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ORDER.getDescription(),
                        poBillMaster.getPlanNo());
            }

            // 供应商
            Supplier supplier = checkSupplierService.check(poBillMaster.getProviderNo());
            poBillMaster.setSupplierId(supplier.getId());
            poBillMaster.setSupplierName(supplier.getSupplierName());

            // 采购员是否存在
            checkUserService.check(poBillMaster.getBuyerNo());
            // 币种
            BaseData currencyBaseData = dataDictService.
                    query(new BaseData((++maxCurrencyId).toString(), poBillMaster.getCurrency(), poBillMaster.getCurrencyName(), poBillMaster.getCurrency(), null),
                            true, true, BaseDataType.CURRENCY);
            poBillMaster.setCurrencyId(Integer.parseInt(currencyBaseData.getId()));
            poBillMaster.setExchangeRate(currencyBaseData.getMemo());
            // 发票类型
            BaseData invoiceTypeBaseData = dataDictService.
                    query(new BaseData((++maxInvoiceId).toString(), poBillMaster.getInvoiceType(), poBillMaster.getInvoiceTypeName(), poBillMaster.getInvoiceType(), null),
                            true, true, BaseDataType.INVOICE_TYPE);
            poBillMaster.setInvoiceId(Integer.parseInt(invoiceTypeBaseData.getId()));
            // 付款方式
            BaseData paymentModeBaseData = dataDictService.
                    query(new BaseData((++maxPaymentId).toString(), poBillMaster.getPaymentMode(), poBillMaster.getPaymentModeName(), poBillMaster.getPaymentMode(), null),
                            true, true, BaseDataType.PAYMENT_MODE);
            poBillMaster.setPaymentCode(paymentModeBaseData.getCode());
            // 价格条款
            BaseData priceTermBaseData = dataDictService.
                    query(new BaseData((++maxPaymentId).toString(), poBillMaster.getPriceTerm(), poBillMaster.getPriceTermName(), poBillMaster.getPriceTerm(), null),
                            true, true, BaseDataType.PRICE_TERM);
            poBillMaster.setPriceTermCode(priceTermBaseData.getCode());
            // 主表ID
            poBillMaster.setId(++maxMasterId);
            // 类型种类
            setMaterialType(poBillMaster);

            for (PoBillDetail poBillDetail : poBillMaster.getPoBillDetails()) {

                // 判断采购申请是否存在
                checkRequestService.check(poBillDetail.getRequestNo(), poBillDetail.getRequestItemId());

                // 物料
                Material material = checkMaterialService.check(poBillDetail.getMaterialNo());
                poBillDetail.setMaterialId(Integer.parseInt(material.getId()));
                poBillDetail.setMaterialNo(material.getCode());
                poBillDetail.setGmm(material.getGmm());
                poBillDetail.setWidth(material.getUnitWidth());
                if (poBillDetail.getRealModel() == null) {
                    poBillDetail.setRealModel(material.getMaterialSpec());
                }

                // 物料单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData((++maxUnitId).toString(), poBillDetail.getUnit(), poBillDetail.getUnitName(), poBillDetail.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                poBillDetail.setUnitId(Integer.parseInt(materialUnitBaseData.getId()));

                // MRP
                MRPRequest mrpRequest = clothUtilMapper.getMRPRequest(poBillDetail.getRequestItemId());
                if (ObjectUtil.isNotNull(mrpRequest)) {
                    BeanUtil.copyProperties(mrpRequest, poBillDetail);
                    if (ObjectUtil.isNotNull(poBillDetail.getProductNo())) {
                        OrderInfo orderInfo = clothUtilMapper.getOrderInfo(poBillDetail.getProductNo());
                        if (ObjectUtil.isNotNull(orderInfo)) {
                            poBillDetail.setLotNo(orderInfo.getLotNo());
                        }
                    }
                }

                // 明细表ID
                poBillDetail.setId(++maxDetailId);
                // 明细表设置主表ID
                poBillDetail.setPoBillMasterId(poBillMaster.getId());

            }
        }

        // 插入到数据库
        poBillMapper.insertBatchHdr(list);
        list.forEach(poBillMaster -> poBillMapper.insertBatchDtl(poBillMaster.getPoBillDetails()));
    }

    @Override
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkPoBillService.check(billCode, "存在到货单");
        }

        poBillMapper.deleteBatchDtl(billCodes);
        poBillMapper.deleteBatchHdr(billCodes);


    }

    /**
     * @Author fyq
     * @Description 设置类型种类
     * @Date 14:33 2021/9/8
     * @Param [poBillMaster]
     * @return void
     **/
    private void setMaterialType(PoBillMaster poBillMaster) throws InterFaceException {

        final List<String> materialNos = new ArrayList<>();
        final Set<String> materialNoSet = new HashSet<>();

        poBillMaster.getPoBillDetails().forEach(poBillDetail -> materialNos.add(poBillDetail.getMaterialNo()));

        List<Material> materials = materialMapper.query(materialNos);
        materials.forEach(material -> materialNoSet.add(material.getLocalType()));

        if (materialNoSet.size() == 0) {
            throw new InterFaceException(StrUtil.format("{}-[物料的物料类型未申明]",
                    StrUtil.blankToDefault(requiredProperties.getSysName(),"未知系统")));
        }
        else if (materialNoSet.size() > 1) {
            throw new InterFaceException(StrUtil.format("{}-[物料的物料类型存在2种或2种以上,只能有一种物料类型]",
            StrUtil.blankToDefault(requiredProperties.getSysName(),"未知系统")));
        }
        else {
            poBillMaster.setMaterialType(materialNoSet.iterator().next());
        }

    }
}
