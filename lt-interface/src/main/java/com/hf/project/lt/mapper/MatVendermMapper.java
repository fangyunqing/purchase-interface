package com.hf.project.lt.mapper;

import com.hf.project.lt.entity.MatVenderm;

import java.util.List;

public interface MatVendermMapper {

    /**
     * @Author fyq
     * @Description 批量插入
     * @Date 15:11 2021/9/20
     * @Param [matVendermList]
     * @return void
     **/
    void insertBatch(List<MatVenderm> matVendermList);

    /**
     * @Author fyq
     * @Description 批量查询
     * @Date 15:12 2021/9/20
     * @Param [NCBillCodes]
     * @return java.util.List<com.hf.project.lt.entity.MatVenderm>
     **/
    List<MatVenderm> query(List<String> NCBillCodes);

    /**
     * @Author fyq
     * @Description 查询
     * @Date 15:12 2021/9/20
     * @Param [NCBillCode]
     * @return com.hf.project.lt.entity.MatVenderm
     **/
    MatVenderm querySingle(String NCBillCode);

    /**
     * @Author fyq
     * @Description 插入
     * @Date 11:29 2021/10/21
     * @Param [matVenderm]
     * @return void
     **/
    void insert(MatVenderm matVenderm);

    /**
     * @Author fyq
     * @Description 更新
     * @Date 11:29 2021/10/21
     * @Param [matVenderm]
     * @return void
     **/
    void update(MatVenderm matVenderm);
}
