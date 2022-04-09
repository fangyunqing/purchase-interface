package com.hf.project.dye.entity;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

/**
 * @Description 采购员
 * @Author fyq
 * @Date 2021/5/8 9:06
 **/

@Data
public class Buyer {

    /** GUID */
    private String guid;

    /** 工号 */
    private String buyerNo;

    /** 姓名 */
    private String buyerName;

    /** 员工GUID */
    private String userGUID;

    /** 组GUID */
    private String groupGUID = "C22ECCA8-1439-4341-9453-A5BC0008C168";



}
