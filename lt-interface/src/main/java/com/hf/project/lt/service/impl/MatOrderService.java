package com.hf.project.lt.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.check.billcode.CheckExistRequestService;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.common.service.datadict.DataDictService;
import com.hf.project.lt.check.billcode.CheckDeleteMatOrderService;
import com.hf.project.lt.check.record.CheckSupplierService;
import com.hf.project.lt.entity.MatVenderm;
import com.hf.project.lt.entity.matorder.MatOrderDtl;
import com.hf.project.lt.entity.matorder.MatOrderHdr;
import com.hf.project.lt.mapper.MatOrderMapper;
import com.hf.project.lt.mapper.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 采购订单服务类
 * @Author fyq
 * @Date 2021/9/23 14:21
 **/

@Service
public class MatOrderService implements PurchaseOrderService<MatOrderHdr> {

    @Autowired
    private MatOrderMapper matOrderMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private UtilMapper utilMapper;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckExistRequestService checkExistRequestService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private CheckDeleteMatOrderService checkDeleteMatOrderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<MatOrderHdr> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(matOrderHdr -> {
            if (matOrderMapper.existBillCode(matOrderHdr.getPlanNo())) {
                existBillCodes.add(matOrderHdr.getPlanNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ORDER.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        for (MatOrderHdr matOrderHdr : list) {

            // 首先判断明细条数是否为空
            if (matOrderHdr.getMatOrderDtls().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ORDER.getDescription(),
                        matOrderHdr.getPlanNo());
            }

            // 供应商
            MatVenderm matVenderm = checkSupplierService.check(matOrderHdr.getProviderNo());
            matOrderHdr.setProviderNo(matVenderm.getVendermId());
            // 币种
            BaseData currencyBaseData = dataDictService.
                    query(new BaseData(null, matOrderHdr.getCurrency(), matOrderHdr.getCurrencyName(), matOrderHdr.getCurrency(), null),
                            true, true, BaseDataType.CURRENCY);
            matOrderHdr.setCurrency(currencyBaseData.getCode());
            matOrderHdr.setExchangeRate(currencyBaseData.getMemo());
            // 付款方式
            BaseData paymentModeBaseData = dataDictService.
                    query(new BaseData(null, matOrderHdr.getPaymentMode(), matOrderHdr.getPaymentModeName(), matOrderHdr.getPaymentMode(), null),
                            true, true, BaseDataType.PAYMENT_MODE);
            matOrderHdr.setPaymentMode(paymentModeBaseData.getCode());
            // 开票方式
            BaseData invoiceTypeBaseData = dataDictService.
                    query(new BaseData(null, matOrderHdr.getInvoiceType(), matOrderHdr.getInvoiceTypeName(), matOrderHdr.getInvoiceType(), null),
                            true, true, BaseDataType.INVOICE_TYPE);
            matOrderHdr.setInvoiceType(invoiceTypeBaseData.getCode());
            // 交货方式
            BaseData deliveryTypeBaseData = dataDictService.
                    query(new BaseData(null, matOrderHdr.getDeliveryType(), matOrderHdr.getDeliveryTypeName(), matOrderHdr.getDeliveryType(), null),
                            true, true, BaseDataType.DELIVERY_TYPE);
            matOrderHdr.setDeliveryType(deliveryTypeBaseData.getCode());

            int index = 0;
            for (MatOrderDtl matOrderDtl : matOrderHdr.getMatOrderDtls()) {

                // 判断采购申请是否存在
                checkExistRequestService.check(matOrderDtl.getRequestNo(), matOrderDtl.getRequestItemId());
                // 物料
                checkMaterialService.check(matOrderDtl.getMaterialNo());
                // 单位
                setMaterialUnit(matOrderDtl);


                ++index;
                String item = String.format("%02d", index);
                String sortNo = String.format("%03d", index);
                matOrderDtl.setItem(item);
                matOrderDtl.setSortNo(sortNo);

                matOrderDtl.setOrderNo(matOrderHdr.getPlanNo());
            }

        }

        matOrderMapper.insertBatchHdr(list);
        list.forEach(matOrderHdr -> matOrderMapper.insertBatchDtl(matOrderHdr.getMatOrderDtls()));

    }


    /**
     * @Author fyq
     * @Description 设置物料单位
     * @Date 14:51 2021/9/8
     * @Param [matOrderHdr]
     * @return void
     *
     **/
    private void setMaterialUnit(MatOrderDtl matOrderDtl) {

        Map<String, String> requestDltUnit = utilMapper.getRequestDltUnit(matOrderDtl.getRequestNo(), matOrderDtl.getRequestItemId());
        matOrderDtl.setUnit(requestDltUnit.get("kcUnit"));
        matOrderDtl.setUnit(requestDltUnit.get("cgUnit"));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeleteMatOrderService.check(billCode, "存在到货单");
        }

        matOrderMapper.deleteBatchDtl(billCodes);
        matOrderMapper.deleteBatchHdr(billCodes);

    }
}
