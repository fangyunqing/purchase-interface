package com.hf.project.zz.mapper.zz;

import com.hf.project.zz.entity.pur.PurDtl;
import com.hf.project.zz.entity.pur.PurHdr;

import java.util.List;

public interface PurMapper {

    /**
     * @Author fyq
     * @Description 批量主表插入
     * @Date 15:58 2021/8/31
     * @Param [purHdrList]
     * @return void
     **/
    void insertBatchHdr(List<PurHdr> purHdrList);

    /**
     * @Author fyq
     * @Description 批量子表插入
     * @Date 15:58 2021/8/31
     * @Param [purDtlList]
     * @return void
     **/
    void insertBatchDtl(List<PurDtl> purDtlList);

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
     * @Description 获取订单明细信息
     * @Date 9:43 2021/10/14
     * @Param [billCode, BIPItemID]
     * @return com.hf.project.zz.entity.pur.PurDtl
     **/
    PurDtl getPurDtl(String billCode, String BIPItemID);

    /**
     * @Author fyq
     * @Description 更新采购订单
     * @Date 13:50 2021/10/14
     * @Param [billCode, BIPItemID]
     * @return void
     **/
    void updatePurRecQty(PurDtl purDtl);
}
