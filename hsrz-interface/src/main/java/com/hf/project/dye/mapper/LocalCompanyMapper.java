package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.LocalCompany;

public interface LocalCompanyMapper {

    /**
     * @Author fyq
     * @Description 查询公司信息
     * @Date 10:28 2021/9/3
     * @Param [companyName]
     * @return com.hf.project.dye.entity.LocalCompany
     **/
    LocalCompany query(String companyName);
}
