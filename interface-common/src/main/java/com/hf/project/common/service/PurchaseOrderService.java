package com.hf.project.common.service;

import com.hf.project.common.exception.InterFaceException;

import java.util.List;

public interface PurchaseOrderService<T> {

    /**
     * @Author fyq
     * @Description 批量插入 如果已经存在 抛出异常
     * @Date 11:10 2021/8/27
     * @Param [list]
     * @return void
     **/
    void insertBatch(List<T> list) throws InterFaceException;

    /**
     * @Author fyq
     * @Description 批量删除 如果无法删除 抛出异常
     * @Date 11:23 2021/8/31
     * @Param [orderNos]
     * @return void
     **/
    void deleteBatch(List<String> billCodes) throws InterFaceException;


}
