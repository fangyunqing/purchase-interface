package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.Provider;

import java.util.List;

public interface ProviderMapper {

    /**
     * @Author fyq
     * @Description 批量插入
     * @Date 9:10 2021/8/27
     * @Param [provider]
     * @return void
     **/
    void insertBatch(List<Provider> providers);

    /**
     * @Author fyq
     * @Description 批量查询
     * @Date 10:04 2021/9/3
     * @Param [billCodes]
     * @return java.util.List<com.hf.project.dye.entity.Provider>
     **/
    List<Provider> query(List<String> billCodes);

    /**
     * @Author fyq
     * @Description  通过NC查询
     * @Date 10:04 2021/9/3
     * @Param [NCBillCode]
     * @return com.hf.project.dye.entity.Provider
     **/
    Provider querySingle(String NCBillCode);

    /**
     * @Author fyq
     * @Description 插入
     * @Date 11:29 2021/10/21
     * @Param [provider]
     * @return void
     **/
    void insert(Provider provider);

    /**
     * @Author fyq
     * @Description 更新
     * @Date 11:29 2021/10/21
     * @Param [provider]
     * @return void
     **/
    void update(Provider provider);

}
