package com.hf.project.zz.mapper.zz;

import com.hf.project.zz.entity.Provider;

import java.util.List;

public interface ProviderMapper {
    /**
     * @Author fyq
     * @Description 批量插入
     * @Date 15:11 2021/9/20
     * @Param [providers]
     * @return void
     **/
    void insertBatch(List<Provider> providers);

    /**
     * @Author fyq
     * @Description 批量查询
     * @Date 15:12 2021/9/20
     * @Param [NCBillCodes]
     * @return java.util.List<com.hf.project.lt.entity.MatVenderm>
     **/
    List<Provider> query(List<String> NCBillCodes);

    /**
     * @Author fyq
     * @Description 查询
     * @Date 15:12 2021/9/20
     * @Param [NCBillCode]
     * @return com.hf.project.lt.entity.MatVenderm
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
