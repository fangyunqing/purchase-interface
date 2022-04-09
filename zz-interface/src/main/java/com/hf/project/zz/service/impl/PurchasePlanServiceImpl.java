package com.hf.project.zz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.check.billcode.CheckExistRequestService;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.check.record.CheckUserService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.User;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.common.service.datadict.DataDictService;
import com.hf.project.zz.check.billcode.CheckDeletePurService;
import com.hf.project.zz.check.record.CheckSupplierService;
import com.hf.project.zz.entity.Provider;
import com.hf.project.zz.entity.pur.PurDtl;
import com.hf.project.zz.entity.pur.PurHdr;
import com.hf.project.zz.mapper.zz.PurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 采购订单服务实现类
 * @Author fyq
 * @Date 2021/10/12 14:24
 **/

@Service
public class PurchasePlanServiceImpl implements PurchaseOrderService<PurHdr> {

    @Autowired
    private PurMapper purMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckUserService checkUserService;

    @Autowired
    private CheckExistRequestService checkExistRequestService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private CheckDeletePurService checkDeletePurService;

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "ZZTransactionManager")
    public void insertBatch(List<PurHdr> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(purchasePlanHdr -> {
            if (purMapper.existBillCode(purchasePlanHdr.getPlanNo())) {
                existBillCodes.add(purchasePlanHdr.getPlanNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ORDER.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        for (PurHdr purHdr : list) {

            // 首先判断明细条数是否为空
            if (purHdr.getPurDtlList().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ORDER.getDescription(),
                        purHdr.getPlanNo());
            }

            // 货币
            BaseData currencyBaseData = dataDictService.
                    query(new BaseData(null, purHdr.getCurrency(), purHdr.getCurrencyName(), purHdr.getCurrency(), null),
                            true, true, BaseDataType.CURRENCY);
            purHdr.setCurrency(currencyBaseData.getCode());
            purHdr.setExchangeRate(currencyBaseData.getMemo());
            // 采购类型
            BaseData contractTypeBaseData = dataDictService.
                    query(new BaseData(null, purHdr.getContractType(), purHdr.getContractTypeName(), purHdr.getContractType(), null),
                            false, true, BaseDataType.CONTRACT_TYPE);
            purHdr.setContractType(contractTypeBaseData.getName());
            // 付款方式
            BaseData paymentModeBaseData = dataDictService.
                    query(new BaseData(null, purHdr.getPaymentMode(), purHdr.getPaymentModeName(), purHdr.getPaymentMode(), null),
                            true, true, BaseDataType.PAYMENT_MODE);
            purHdr.setPaymentMode(paymentModeBaseData.getName());
            // 开票方式
            BaseData invoiceTypeBaseData = dataDictService.
                    query(new BaseData(null, purHdr.getInvoiceType(), purHdr.getInvoiceTypeName(), purHdr.getInvoiceType(), null),
                            true, true, BaseDataType.INVOICE_TYPE);
            purHdr.setInvoiceType(invoiceTypeBaseData.getName());
            // 供应商
            Provider provider = checkSupplierService.check(purHdr.getProviderNo());
            purHdr.setSupplierName(provider.getProviderName());
            // 采购员
            User buyer = checkUserService.check(purHdr.getBuyerNo());
            purHdr.setBuyerName(buyer.getUserName());
            // 创建人
            User creator = checkUserService.check(purHdr.getCreator());
            purHdr.setCreator(creator.getUserName());
            // 审核人
            User confirm = checkUserService.check(purHdr.getConfirmMan());
            purHdr.setConfirmMan(confirm.getUserName());

            for (PurDtl purDtl : purHdr.getPurDtlList()) {

                // 判断采购申请是否存在
                checkExistRequestService.check(purDtl.getRequestNo(), purDtl.getRequestItemId());
                // 物料
                Material material = checkMaterialService.check(purDtl.getMaterialNo());
                purDtl.setMtlName(material.getName());
                purDtl.setMtlSpec(material.getMaterialSpec());
                // 单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData(null, purDtl.getUnit(), purDtl.getUnitName(), purDtl.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                purDtl.setUnit(materialUnitBaseData.getCode());
                // 税率修改
                purDtl.setTaxRate(purDtl.getTaxRate() / 100);
                // 从主表设置子表信息
                purDtl.setPurNo(purHdr.getPlanNo());
            }
        }

        // 插入到数据库
        purMapper.insertBatchHdr(list);
        list.forEach(purHdr -> purMapper.insertBatchDtl(purHdr.getPurDtlList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "ZZTransactionManager")
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeletePurService.check(billCode, "存在到货单");
        }

        purMapper.deleteBatchDtl(billCodes);
        purMapper.deleteBatchHdr(billCodes);

    }
}
