package com.hf.project.zz.mapper.zz;

import com.hf.project.zz.entity.send.SendDetail;
import com.hf.project.zz.entity.send.SendHead;

import java.util.List;

public interface SendMapper {

    /**
     * @Author fyq
     * @Description 批量主表插入
     * @Date 15:58 2021/8/31
     * @Param [sendHeadList]
     * @return void
     **/
    void insertBatchHdr(List<SendHead> sendHeadList);

    /**
     * @Author fyq
     * @Description 批量子表插入
     * @Date 15:58 2021/8/31
     * @Param [sendDetailList]
     * @return void
     **/
    void insertBatchDtl(List<SendDetail> sendDetailList);

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
     * @Description oracle序列
     * @Date 16:19 2021/11/26
     * @Param []
     * @return java.lang.Long
     **/
    Long getNextVal();

}
