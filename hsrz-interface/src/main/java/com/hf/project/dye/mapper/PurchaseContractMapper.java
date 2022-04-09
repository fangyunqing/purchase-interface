package com.hf.project.dye.mapper;


import com.hf.project.dye.entity.purchasecontract.PurchaseContractDtl;
import com.hf.project.dye.entity.purchasecontract.PurchaseContractHdr;

import java.util.List;

public interface PurchaseContractMapper {

    /**
     * @Author fyq
     * @Description 批量 主表 插入
     * @Date 9:10 2021/8/27
     * @Param [purchaseContractHdrList]
     * @return void
     **/
    void insertBatchHdr(List<PurchaseContractHdr> purchaseContractHdrList);

    /**
     * @Author fyq
     * @Description 批量 子表 更新
     * @Date 9:10 2021/8/27
     * @Param [purchaseContractDtls]
     * @return void
     **/
    void insertBatchDtl(List<PurchaseContractDtl> purchaseContractDtls);

    /**
     * @Author fyq
     * @Description 批量更新可用字段
     * @Date 11:08 2021/8/27
     * @Param [orderNos,usable]
     * @return void
     **/
    void updateUsableBatch(List<String> billCodes, Boolean usable);

    /**
     * @Author fyq
     * @Description 是否存在
     * @Date 8:23 2021/9/4
     * @Param [billCode]
     * @return java.lang.Boolean
     **/
    Boolean existBillCode(String billCode);

}
