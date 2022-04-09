package com.hf.project.common.service;

import com.hf.project.common.exception.InterFaceException;

import java.util.List;

public interface SupplierService<T> {

    /**
     * @Author fyq
     * @Description 批量插入 已经存在抛出异常
     * @Date 11:10 2021/8/27
     * @Param [list]
     * @return void
     **/
    void insertBatch(List<T> list) throws InterFaceException;

}
