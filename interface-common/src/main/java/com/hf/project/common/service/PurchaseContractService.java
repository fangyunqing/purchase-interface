package com.hf.project.common.service;

import com.hf.project.common.exception.InterFaceException;

import java.util.List;

public interface PurchaseContractService<T> {

    /**
     * @Author fyq
     * @Description 批量插入 或 存在
     * @Date 11:10 2021/8/27
     * @Param [list]
     * @return void
     **/
    void insertBatch(List<T> list) throws InterFaceException;


    /**
     * @Author fyq
     * @Description 批量失效
     * @Date 11:00 2021/9/1
     * @Param [orderNos]
     * @return void
     **/
    void disableBatch(List<String> billCodes);



}
