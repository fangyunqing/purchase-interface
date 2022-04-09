package com.hf.project.dye.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.check.billcode.CheckExistRequestService;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.User;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.enums.UserRole;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.entity.upload.PurchaseRequest;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.NoExistUserException;
import com.hf.project.common.exception.NoSuchBuyerException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.mapper.common.PurchaseRequestMapper;
import com.hf.project.common.mapper.common.UserMapper;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.common.service.datadict.DataDictService;
import com.hf.project.dye.check.billcode.CheckDeletePurchasePlanService;
import com.hf.project.dye.check.record.CheckSupplierService;
import com.hf.project.dye.entity.BillNoteType;
import com.hf.project.dye.entity.Buyer;
import com.hf.project.dye.entity.LocalCompany;
import com.hf.project.dye.entity.Provider;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanDtl;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;
import com.hf.project.dye.exception.NoNoteTypeException;
import com.hf.project.dye.mapper.BuyerMapper;
import com.hf.project.dye.mapper.LocalCompanyMapper;
import com.hf.project.dye.mapper.PurchasePlanMapper;
import com.hf.project.dye.util.BillNoteTypeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 采购订单服务实现类
 * @Author fyq
 * @Date 2021/9/1 9:38
 **/

@Service
public class PurchasePlanServiceImpl implements PurchaseOrderService<PurchasePlanHdr> {

    private static final Logger log = LogManager.getRootLogger();

    @Autowired
    private PurchasePlanMapper purchasePlanMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private LocalCompanyMapper localCompanyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private CheckDeletePurchasePlanService checkDeletePurchasePlanService;

    @Autowired
    private CheckExistRequestService checkExistRequestService;

    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;

    @Autowired
    private BillNoteTypeUtil billNoteTypeUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<PurchasePlanHdr> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(purchasePlanHdr -> {
            if (purchasePlanMapper.existBillCode(purchasePlanHdr.getPlanNo())) {
                existBillCodes.add(purchasePlanHdr.getPlanNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ORDER.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        for (PurchasePlanHdr purchasePlanHdr : list) {

            // 首先判断明细条数是否为空
            if (purchasePlanHdr.getPurchasePlanDtlList().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ORDER.getDescription(),
                        purchasePlanHdr.getPlanNo());
            }

            // 采购类型
            BaseData contractTypeBaseData = dataDictService.
                    query(new BaseData(null, purchasePlanHdr.getContractType(), purchasePlanHdr.getContractTypeName(), purchasePlanHdr.getContractType(), null),
                            false, true, BaseDataType.CONTRACT_TYPE);
            purchasePlanHdr.setContractType(contractTypeBaseData.getName());
            // 发票类型
            BaseData invoiceTypeBaseData = dataDictService.
                    query(new BaseData(null, purchasePlanHdr.getInvoiceType(), purchasePlanHdr.getInvoiceTypeName(), purchasePlanHdr.getInvoiceType(), null),
                            true, true, BaseDataType.INVOICE_TYPE);
            purchasePlanHdr.setInvoiceType(invoiceTypeBaseData.getName());
            // 付款方式
            BaseData paymentModeBaseData = dataDictService.
                    query(new BaseData(null, purchasePlanHdr.getPaymentMode(), purchasePlanHdr.getPaymentModeName(), purchasePlanHdr.getPaymentMode(), null),
                            true, true, BaseDataType.PAYMENT_MODE);
            purchasePlanHdr.setPaymentMode(paymentModeBaseData.getName());
            // 币种
            BaseData currencyBaseData = dataDictService.
                    query(new BaseData(null, purchasePlanHdr.getCurrency(), purchasePlanHdr.getCurrencyName(), purchasePlanHdr.getCurrency(), null),
                            true, true, BaseDataType.CURRENCY);
            purchasePlanHdr.setCurrency(currencyBaseData.getCode());
            purchasePlanHdr.setExchangeRate(currencyBaseData.getMemo());
            // 设置原始供应商
            Provider orgProvider = checkSupplierService.check(purchasePlanHdr.getOrgProviderNo());
            purchasePlanHdr.setOrgProviderName(orgProvider.getProviderName());
            purchasePlanHdr.setOrgProviderNo(orgProvider.getProviderNo());
            purchasePlanHdr.setOrgProviderGUID(orgProvider.getGuid());
            // 设置供应商
            Provider provider = checkSupplierService.check(purchasePlanHdr.getProviderNo());
            purchasePlanHdr.setProviderName(provider.getProviderName());
            purchasePlanHdr.setProviderNo(provider.getProviderNo());
            purchasePlanHdr.setProviderGUID(provider.getGuid());
            // 设置采购员
            setBuyer(purchasePlanHdr);
            // 设置公司ID
            LocalCompany localCompany = localCompanyMapper.query(purchasePlanHdr.getCompanyName());
            if (ObjectUtil.isNotNull(localCompany)){
                purchasePlanHdr.setCompanyID(localCompany.getCompanyID());
            }

            Set<Integer> noteTypeIdSet = new HashSet<>();

            for (PurchasePlanDtl purchasePlanDtl : purchasePlanHdr.getPurchasePlanDtlList()) {

                // 判断采购申请是否存在
                checkExistRequestService.check(purchasePlanDtl.getRequestNo(), purchasePlanDtl.getRequestItemId());
                // 获取订单的NoteTypeId
                List<String> billCodes = new ArrayList<>();
                billCodes.add(purchasePlanDtl.getRequestNo());
                PurchaseRequest pr = purchaseRequestMapper.query(billCodes)
                        .stream()
                        .filter(purchaseRequest -> purchasePlanDtl.getRequestNo().equals(purchaseRequest.getBillCode())
                                && purchasePlanDtl.getRequestItemId().equals(purchaseRequest.getItemId()))
                        .findFirst().orElse(null);
                if (ObjectUtil.isNotNull(pr) && ObjectUtil.isNotNull(pr.getDefine1())) {
                    int noteTypeId;
                    try {
                        noteTypeId = Integer.parseInt(pr.getDefine1());
                        BillNoteType billNoteType = billNoteTypeUtil.queryByRequest(noteTypeId);
                        if (ObjectUtil.isNotNull(billNoteType)) {
                            noteTypeIdSet.add(billNoteType.getPurchasePlanNoteTypeId());
                        }
                    }
                    catch (Exception e) {
                        log.debug(e);
                    }
                }

                // 物料信息
                Material material = checkMaterialService.check(purchasePlanDtl.getMaterialNo());
                purchasePlanDtl.setMaterialGUID(material.getId());
                purchasePlanDtl.setMaterialName(material.getName());
                purchasePlanDtl.setMaterialNo(material.getCode());
                // 物料单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData(null, purchasePlanDtl.getUnit(), purchasePlanDtl.getUnitName(), purchasePlanDtl.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                purchasePlanDtl.setUnit(materialUnitBaseData.getCode());
                // 设置主GUID
                purchasePlanDtl.setParentGUID(purchasePlanHdr.getGuid());
            }

            if (noteTypeIdSet.size() != 1) {
                throw new NoNoteTypeException();
            }

            purchasePlanHdr.setNoteTypeID(noteTypeIdSet.iterator().next());

        }


        // 插入到数据库
        purchasePlanMapper.insertBatchHdr(list);
        list.forEach(purchasePlanHdr -> purchasePlanMapper.insertBatchDtl(purchasePlanHdr.getPurchasePlanDtlList()));


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeletePurchasePlanService.check(billCode, "存在到货单");
        }

        purchasePlanMapper.deleteBatchDtl(billCodes);
        purchasePlanMapper.deleteBatchHdr(billCodes);

    }

    /**
     * @Author fyq
     * @Description 设置采购员
     * @Date 10:03 2021/11/24
     * @Param [purchasePlanHdr]
     * @return void
     **/
    private void setBuyer(PurchasePlanHdr purchasePlanHdr) throws NoSuchBuyerException, NoExistUserException {

        Buyer buyer = buyerMapper.query(purchasePlanHdr.getBuyerNo());
        if (ObjectUtil.isNull(buyer)) {
            if (requiredProperties.getDictInsert()) {
                User user = userMapper.query(purchasePlanHdr.getBuyerNo());
                if (ObjectUtil.isNotNull(user)) {
                    buyer = new Buyer();
                    buyer.setGuid(IdUtil.randomUUID());
                    buyer.setBuyerNo(user.getUserCode());
                    buyer.setBuyerName(user.getUserName());
                    buyer.setUserGUID(user.getId());
                    buyerMapper.insert(buyer);
                }
                else {
                    throw new NoExistUserException(requiredProperties.getSysName(), purchasePlanHdr.getBuyerNo(),
                            UserRole.BUYER.getDescription());
                }
            }
            else {
                throw new NoSuchBuyerException(requiredProperties.getSysName(),
                        purchasePlanHdr.getBuyerNo());
            }

        }

        if (ObjectUtil.isNotNull(buyer)){
            purchasePlanHdr.setBuyersGUID(buyer.getGuid());
            purchasePlanHdr.setBuyerNo(buyer.getBuyerNo());
            purchasePlanHdr.setBuyerName(buyer.getBuyerName());
        }
    }
}
