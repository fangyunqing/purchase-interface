package com.hf.project.cloth.mapper;

import com.hf.project.cloth.entity.po.PoBillDetail;
import com.hf.project.cloth.entity.po.PoBillMaster;

import java.util.List;

public interface PoBillMapper {

    /**
     * @Author fyq
     * @Description 批量插入主表
     * @Date 10:59 2021/9/8
     * @Param [poBillMasters]
     * @return void
     **/
    void insertBatchHdr(List<PoBillMaster> poBillMasters);

    /**
     * @Author fyq
     * @Description 批量插入子表
     * @Date 11:00 2021/9/8
     * @Param [poBillDetails]
     * @return void
     **/
    void insertBatchDtl(List<PoBillDetail> poBillDetails);

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
