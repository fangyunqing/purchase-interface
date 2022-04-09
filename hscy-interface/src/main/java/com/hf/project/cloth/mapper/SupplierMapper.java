package com.hf.project.cloth.mapper;

import com.hf.project.cloth.entity.Supplier;

import java.util.List;

public interface SupplierMapper {

    /**
     * @Author fyq
     * @Description 批量插入
     * @Date 9:10 2021/8/27
     * @Param [suppliers]
     * @return void
     **/
    void insertBatch(List<Supplier> suppliers);

    /**
     * @Author fyq
     * @Description 批量查询
     * @Date 14:55 2021/9/7
     * @Param [NCBillCodes]
     * @return java.util.List<com.hf.project.cloth.entity.Supplier>
     **/
    List<Supplier> query(List<String> NCBillCodes);

    /**
     * @Author fyq
     * @Description 通过NC查询
     * @Date 14:55 2021/9/7
     * @Param [NCBillCode]
     * @return com.hf.project.cloth.entity.Supplier
     **/
    Supplier querySingle(String NCBillCode);

    /**
     * @Author fyq
     * @Description 获取最大ID值
     * @Date 15:38 2021/9/7
     * @Param
     * @return
     **/
    Integer getMaxId();

    /**
     * @Author fyq
     * @Description 插入
     * @Date 11:29 2021/10/21
     * @Param [supplier]
     * @return void
     **/
    void insert(Supplier supplier);

    /**
     * @Author fyq
     * @Description 更新
     * @Date 11:29 2021/10/21
     * @Param [supplier]
     * @return void
     **/
    void update(Supplier supplier);

}
