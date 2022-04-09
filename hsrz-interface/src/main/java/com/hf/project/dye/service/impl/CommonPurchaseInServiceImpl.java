package com.hf.project.dye.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.exception.billcodeexception.NoDetailsBillCodeException;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.common.service.datadict.DataDictService;
import com.hf.project.dye.check.billcode.CheckDeleteCommonPurchaseInService;
import com.hf.project.dye.check.billcode.CheckExistPurchasePlanService;
import com.hf.project.dye.check.record.CheckSupplierService;
import com.hf.project.dye.entity.BillNoteType;
import com.hf.project.dye.entity.Provider;
import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInDtl;
import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInHdr;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;
import com.hf.project.dye.exception.NoNoteTypeException;
import com.hf.project.dye.mapper.CommonPurchaseInMapper;
import com.hf.project.dye.mapper.DyeUtilMapper;
import com.hf.project.dye.util.BillNoteTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 采购到货服务类
 * @Author fyq
 * @Date 2021/9/1 10:08
 **/

@Service
public class CommonPurchaseInServiceImpl implements PurchaseArrivalService<CommonPurchaseInHdr> {

    @Autowired
    private CommonPurchaseInMapper commonPurchaseInMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckExistPurchasePlanService checkExistPurchasePlanService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckDeleteCommonPurchaseInService checkDeleteCommonPurchaseInService;

    @Autowired
    private DyeUtilMapper dyeUtilMapper;

    @Autowired
    private BillNoteTypeUtil billNoteTypeUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<CommonPurchaseInHdr> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(commonPurchaseInHdr -> {
            if (commonPurchaseInMapper.existBillCode(commonPurchaseInHdr.getPurchaseInNo())) {
                existBillCodes.add(commonPurchaseInHdr.getPurchaseInNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ARRIVAL.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        for (CommonPurchaseInHdr commonPurchaseInHdr : list) {

            // 判断明细是有条数
            if (commonPurchaseInHdr.getCommonPurchaseInDtlList().size() == 0) {
                throw new NoDetailsBillCodeException(requiredProperties.getSysName(),
                        BillCodeType.PURCHASE_ARRIVAL.getDescription(),
                        commonPurchaseInHdr.getPurchaseInNo());
            }

            // 供应商
            Provider provider = checkSupplierService.check(commonPurchaseInHdr.getProviderNo());
            commonPurchaseInHdr.setProviderNo(provider.getProviderNo());
            commonPurchaseInHdr.setProviderGUID(provider.getGuid());
            commonPurchaseInHdr.setProviderName(provider.getProviderName());
            // 仓库默认为 华锦染化料总仓
            commonPurchaseInHdr.setStoreGUID("9C61B604-FA28-45FA-9C43-A7560102DC9E");
            commonPurchaseInHdr.setStoreName("华锦染化料总仓");
            commonPurchaseInHdr.setStoreNo("DCHJ");

            Set<Integer> noteTypeIdSet = new HashSet<>();

            for (CommonPurchaseInDtl commonPurchaseInDtl : commonPurchaseInHdr.getCommonPurchaseInDtlList()) {

                // 判断采购订单明细是否存在
                String dltGUID = checkExistPurchasePlanService.check(commonPurchaseInDtl.getPlanNo(), commonPurchaseInDtl.getPlanItemId());
                commonPurchaseInDtl.setPurchasePlanDtlGUID(dltGUID);
                // 获取到货的NoteTypeId
                PurchasePlanHdr purchasePlanHdr = dyeUtilMapper.queryPurchasePlanByPlanNo(commonPurchaseInDtl.getPlanNo());
                if (ObjectUtil.isNotNull(purchasePlanHdr)) {
                    BillNoteType billNoteType = billNoteTypeUtil.queryByPurchasePlan(purchasePlanHdr.getNoteTypeID());
                    if (ObjectUtil.isNotNull(billNoteType)) {
                        noteTypeIdSet.add(billNoteType.getCommonPurchaseInNoteTypeId());
                    }
                }
                // 物料
                Material material = checkMaterialService.check(commonPurchaseInDtl.getMaterialNo());
                commonPurchaseInDtl.setMaterialGUID(material.getId());
                commonPurchaseInDtl.setMaterialName(material.getName());
                commonPurchaseInDtl.setMaterialNo(material.getCode());
                // 设置单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData(null, commonPurchaseInDtl.getUnit(), commonPurchaseInDtl.getUnitName(), commonPurchaseInDtl.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                commonPurchaseInDtl.setUnit(materialUnitBaseData.getCode());
                // 设置扩展单位
                if (ObjectUtil.isNotNull(commonPurchaseInDtl.getPkgUnitEx()) && ObjectUtil.isNotNull(commonPurchaseInDtl.getPkgUnitExName())) {
                    BaseData materialUnitExBaseData = dataDictService.
                            query(new BaseData(null, commonPurchaseInDtl.getPkgUnitEx(), commonPurchaseInDtl.getPkgUnitExName(), commonPurchaseInDtl.getPkgUnitEx(), null),
                                    true, true, BaseDataType.MATERIAL_UNIT);
                    commonPurchaseInDtl.setPkgUnitEx(materialUnitExBaseData.getCode());
                }

                commonPurchaseInDtl.setParentGUID(commonPurchaseInHdr.getGuid());

            }


            if (noteTypeIdSet.size() != 1) {
                throw new NoNoteTypeException();
            }

            commonPurchaseInHdr.setNoteTypeID(noteTypeIdSet.iterator().next());
            BillNoteType billNoteType = billNoteTypeUtil.queryByCommonPurchaseIn(commonPurchaseInHdr.getNoteTypeID());
            if (ObjectUtil.isNotNull(billNoteType)) {
                commonPurchaseInHdr.setType(billNoteType.getTypeName());
            }
        }

        // 插入到数据库
        commonPurchaseInMapper.insertBatchHdr(list);
        list.forEach(commonPurchaseInHdr -> commonPurchaseInMapper.insertBatchDtl(commonPurchaseInHdr.getCommonPurchaseInDtlList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeleteCommonPurchaseInService.check(billCode, "存在入库单");
        }

        commonPurchaseInMapper.deleteBatchHdr(billCodes);
        commonPurchaseInMapper.deleteBatchDtl(billCodes);
    }
}
