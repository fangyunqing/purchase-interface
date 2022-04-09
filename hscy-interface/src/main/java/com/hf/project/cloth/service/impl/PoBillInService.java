package com.hf.project.cloth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hf.project.cloth.check.billcode.CheckDeletePoBillInService;
import com.hf.project.cloth.check.billcode.CheckExistPoBillService;
import com.hf.project.cloth.check.record.CheckSupplierService;
import com.hf.project.cloth.entity.Supplier;
import com.hf.project.cloth.entity.poin.CommonPoBillInDtl;
import com.hf.project.cloth.entity.poin.CommonPoBillInHdr;
import com.hf.project.cloth.mapper.ClothUtilMapper;
import com.hf.project.cloth.mapper.PoBillInMapper;
import com.hf.project.common.check.record.CheckMaterialService;
import com.hf.project.common.entity.BaseData;
import com.hf.project.common.entity.enums.BaseDataType;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.common.service.datadict.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 到货服务类
 * @Author fyq
 * @Date 2021/9/9 8:31
 **/

@Service
public class PoBillInService implements PurchaseArrivalService<CommonPoBillInHdr> {

    @Autowired
    private PoBillInMapper poBillInMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Autowired
    private ClothUtilMapper clothUtilMapper;

    @Autowired
    private CheckSupplierService checkSupplierService;

    @Autowired
    private CheckExistPoBillService checkExistPoBillService;

    @Autowired
    private CheckMaterialService checkMaterialService;

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private CheckDeletePoBillInService checkDeletePoBillInService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<CommonPoBillInHdr> list) throws InterFaceException {

        // 判断单据号是否已经存在
        final List<String> existBillCodes = new ArrayList<>();

        list.forEach(commonPoBillInHdr -> {
            if (poBillInMapper.existBillCode(commonPoBillInHdr.getPurchaseInNo())) {
                existBillCodes.add(commonPoBillInHdr.getProviderNo());
            }
        });

        if (existBillCodes.size() > 0) {
            throw new ExistBillCodeException(requiredProperties.getSysName(),
                    BillCodeType.PURCHASE_ARRIVAL.getDescription(),
                    StrUtil.join(",", existBillCodes));
        }

        Integer maxDetailId = poBillInMapper.getMaxDetailId();
        Integer maxMasterId = poBillInMapper.getMaxMasterId();
        Integer maxUnitId = clothUtilMapper.getMaxUnitId();

        for (CommonPoBillInHdr commonPoBillInHdr : list) {

            // 供应商
            Supplier supplier = checkSupplierService.check(commonPoBillInHdr.getProviderNo());
            commonPoBillInHdr.setSupplierId(supplier.getId());
            commonPoBillInHdr.setSupplierName(supplier.getSupplierName());

            commonPoBillInHdr.setId(++maxMasterId);

            for (CommonPoBillInDtl commonPoBillInDtl : commonPoBillInHdr.getCommonPoBillInDtls()) {

                // 判断采购订单是否存在
                String dltKey = checkExistPoBillService.check(commonPoBillInDtl.getPlanNo(), commonPoBillInDtl.getPlanItemId());
                commonPoBillInDtl.setPoBillDtlID(Integer.parseInt(dltKey));
                // 物料
                Material material = checkMaterialService.check(commonPoBillInDtl.getMaterialNo());
                commonPoBillInDtl.setMaterialId(Integer.parseInt(material.getId()));
                commonPoBillInDtl.setMaterialNo(material.getCode());
                // 物料单位
                BaseData materialUnitBaseData = dataDictService.
                        query(new BaseData((++maxUnitId).toString(), commonPoBillInDtl.getUnit(), commonPoBillInDtl.getUnitName(), commonPoBillInDtl.getUnit(), null),
                                true, true, BaseDataType.MATERIAL_UNIT);
                commonPoBillInDtl.setUnitId(Integer.parseInt(materialUnitBaseData.getId()));

                commonPoBillInDtl.setId(++maxDetailId);
                commonPoBillInDtl.setPobillInHdrID(commonPoBillInHdr.getId());
            }

        }

        // 插入到数据库
        poBillInMapper.insertBatchHdr(list);
        list.forEach(commonPoBillInHdr -> poBillInMapper.insertBatchDtl(commonPoBillInHdr.getCommonPoBillInDtls()));

    }

    @Override
    public void deleteBatch(List<String> billCodes) throws InterFaceException {

        for (String billCode : billCodes) {
            checkDeletePoBillInService.check(billCode, "存在入库单");
        }

        poBillInMapper.deleteBatchDtl(billCodes);
        poBillInMapper.deleteBatchHdr(billCodes);
    }
}
