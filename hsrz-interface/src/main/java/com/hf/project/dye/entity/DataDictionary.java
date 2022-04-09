package com.hf.project.dye.entity;

import lombok.Data;

/**
 * @Description 数据字典
 * @Author fyq
 * @Date 2021/6/21 11:07
 **/

@Data
public class DataDictionary {

    public static final String InvoiceType = "InvoiceType";

    public static final String PaymentMode = "PaymentMode";

    /** 值名称 */
    private String valueName;

    /** 组名称 */
    private String valueGroup;

    /** GUID */
    private String guid;

    /** 类型编码 */
    private String typeCode;

}
