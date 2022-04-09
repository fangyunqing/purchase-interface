package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.purchaseplan.PurchasePlanDtl;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;

import java.util.List;

public interface PurchasePlanMapper {

    /**
     * @Author fyq
     * @Description 批量主表插入
     * @Date 15:58 2021/8/31
     * @Param [purchasePlanHdrList]
     * @return void
     **/
    void insertBatchHdr(List<PurchasePlanHdr> purchasePlanHdrList);

    /**
     * @Author fyq
     * @Description 批量子表插入
     * @Date 15:58 2021/8/31
     * @Param [purchasePlanDtlList]
     * @return void
     **/
    void insertBatchDtl(List<PurchasePlanDtl> purchasePlanDtlList);

    /**
     * @Author fyq
     * @Description 是否可以弃审
     * @Date 15:58 2021/8/31
     * @Param [billCode]
     * @return java.lang.Boolean
     **/
    Boolean canDelete(String billCode);

    /**
     * @Author fyq
     * @Description 批量删除主表
     * @Date 15:58 2021/8/31
     * @Param [billCodes]
     * @return void
     **/
    void deleteBatchHdr(List<String> billCodes);

    /**
     * @Author fyq
     * @Description 批量删除子表
     * @Date 15:57 2021/8/31
     * @Param [billCodes]
     * @return void
     **/
    void deleteBatchDtl(List<String> billCodes);

    /**
     * @Author fyq
     * @Description 单号是否存在
     * @Date 9:02 2021/9/3
     * @Param [billCode]
     * @return java.lang.Boolean
     **/
    Boolean existBillCode(String billCode);

    /**
     * @Author fyq
     * @Description 获取采购订单明细主键
     * @Date 15:06 2021/9/3
     * @Param [billCode, BIPItemCode]
     * @return java.lang.String
     **/
    String getDtlKey(String billCode, String BIPItemId);
}
