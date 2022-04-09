package com.hf.project.dye.service.impl;

import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.mapper.common.MaterialMapper;
import com.hf.project.common.service.PurchaseContractService;
import com.hf.project.dye.check.record.CheckSupplierService;
import com.hf.project.dye.entity.Provider;
import com.hf.project.dye.entity.purchasecontract.PurchaseContractDtl;
import com.hf.project.dye.entity.purchasecontract.PurchaseContractHdr;
import com.hf.project.dye.mapper.PurchaseContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 采购合同处理
 * @Author fyq
 * @Date 2021/8/27 11:13
 **/

@Service
public class PurchaseContractServiceImpl implements PurchaseContractService<PurchaseContractHdr> {

    @Autowired
    private PurchaseContractMapper purchaseContractMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<PurchaseContractHdr> list) throws InterFaceException {

        final List<String> disableBillCodes = new ArrayList<>();

        // 判断合同已经存在
        list.forEach(purchaseContractHdr -> {
            if (purchaseContractMapper.existBillCode(purchaseContractHdr.getContractNo())) {
                disableBillCodes.add(purchaseContractHdr.getContractNo());
            }
        });

        for (PurchaseContractHdr purchaseContractHdr : list) {

            // 判断明细是有条数
            if (purchaseContractHdr.getPurchaseContractDtlList().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_CONTRACT.getDescription(),
                        purchaseContractHdr.getContractNo());
            }

            // 供应商
            Provider orgProvider = checkSupplierService.check(purchaseContractHdr.getProviderNo());
            purchaseContractHdr.setProviderName(orgProvider.getProviderName());
            purchaseContractHdr.setProviderNo(orgProvider.getProviderNo());
            purchaseContractHdr.setProviderGUID(orgProvider.getGuid());

            for (PurchaseContractDtl purchaseContractDtl : purchaseContractHdr.getPurchaseContractDtlList()) {

                // 物料
                Material material = checkMaterialService.check(purchaseContractDtl.getMaterialNo());
                purchaseContractDtl.setMaterialGUID(material.getIid());
                purchaseContractDtl.setMaterialName(material.getName());
                purchaseContractDtl.setMaterialNo(material.getCode());
                purchaseContractDtl.setUnit(material.getUnit());
                // 设置主键
                purchaseContractDtl.setParentGUID(purchaseContractHdr.getGuid());
            }
        }

        if (disableBillCodes.size() > 0) {
            purchaseContractMapper.updateUsableBatch(disableBillCodes, false);
        }

        if (list.size() > 0) {
            purchaseContractMapper.insertBatchHdr(list);
            list.forEach(purchaseContractHdr -> purchaseContractMapper.insertBatchDtl(purchaseContractHdr.getPurchaseContractDtlList()));
        }



    }

    @Override
    public void disableBatch(List<String> billCodes) {
        purchaseContractMapper.updateUsableBatch(billCodes, false);
    }

}
