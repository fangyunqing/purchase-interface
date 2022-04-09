package com.hf.project.lt.mapper;

import com.hf.project.lt.entity.matorder.MatOrderDtl;
import com.hf.project.lt.entity.matorder.MatOrderHdr;

import java.util.List;

public interface MatOrderMapper {

    /**
     * @Author fyq
     * @Description 批量插入主表
     * @Date 10:59 2021/9/8
     * @Param [matOrderHdrList]
     * @return void
     **/
    void insertBatchHdr(List<MatOrderHdr> matOrderHdrList);

    /**
     * @Author fyq
     * @Description 批量插入子表
     * @Date 11:00 2021/9/8
     * @Param [matOrderDtlList]
     * @return void
     **/
    void insertBatchDtl(List<MatOrderDtl> matOrderDtlList);

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
     * @Description 是否可以弃审
     * @Date 15:58 2021/8/31
     * @Param [billCode]
     * @return java.lang.Boolean
     **/
    Boolean canDelete(String billCode);

}
