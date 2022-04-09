package com.hf.project.dye.entity;

import lombok.Data;

/**
 * @Description 公司信息
 * @Author fyq
 * @Date 2021/5/12 11:13
 **/

@Data
public class LocalCompany {

    /** 公司ID */
    private Integer companyID;

    /** 公司名称 */
    private String companyName;

    /** 公司全称 */
    private String companyFullName;

}
