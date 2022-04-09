package com.hf.project.cloth.mapper;

import com.hf.project.cloth.entity.poin.CommonPoBillInDtl;
import com.hf.project.cloth.entity.poin.CommonPoBillInHdr;

import java.util.List;

public interface PoBillInMapper {

    /**
     * @Author fyq
     * @Description 批量插入主表
     * @Date 10:59 2021/9/8
     * @Param [commonPoBillInHdrList]
     * @return void
     **/
    void insertBatchHdr(List<CommonPoBillInHdr> commonPoBillInHdrList);

    /**
     * @Author fyq
     * @Description 批量插入子表
     * @Date 11:00 2021/9/8
     * @Param [commonPoBillInDtlList]
     * @return void
     **/
    void insertBatchDtl(List<CommonPoBillInDtl> commonPoBillInDtlList);

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
     * @Description 主表的最大ID
     * @Date 11:02 2021/9/8
     * @Param []
     * @return java.lang.String
     **/
    Integer getMaxMasterId();

    /**
     * @Author fyq
     * @Description 子表的最大ID
     * @Date 11:03 2021/9/8
     * @Param []
     * @return java.lang.String
     **/
    Integer getMaxDetailId();


    /**
     * @Author fyq
     * @Description 是否可以弃审
     * @Date 15:58 2021/8/31
     * @Param [billCode]
     * @return java.lang.Boolean
     **/
    Boolean canDelete(String billCode);

}
