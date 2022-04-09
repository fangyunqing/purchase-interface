package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInDtl;
import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInHdr;

import java.util.List;

public interface CommonPurchaseInMapper {

    /**
     * @Author fyq
     * @Description 批量主表插入
     * @Date 15:58 2021/8/31
     * @Param [commonPurchaseInHdrList]
     * @return void
     **/
    void insertBatchHdr(List<CommonPurchaseInHdr> commonPurchaseInHdrList);

    /**
     * @Author fyq
     * @Description 批量子表插入
     * @Date 15:58 2021/8/31
     * @Param [commonPurchaseInDtlList]
     * @return void
     **/
    void insertBatchDtl(List<CommonPurchaseInDtl> commonPurchaseInDtlList);

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
}
