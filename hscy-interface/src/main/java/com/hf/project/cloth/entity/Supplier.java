package com.hf.project.cloth.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Description 成衣供应商
 * @Author fyq
 * @Date 2021/9/7 14:40
 **/

@Data
public class Supplier {

    /** 自增列 */
    @JSONField(deserialize = false)
    private Integer id;

    /** 供应商编码 */
    @JSONField(name = "code")
    @NotBlank(message = "供应商编码不能为空")
    private String supplierCode;

    /** 供应名称 */
    @JSONField(name = "name")
    @NotBlank(message = "供应商编码不能为空")
    private String supplierName;

    /** 供应商分类 默认值 Y;F;T;P */
    @JSONField(serialize = false)
    private String supplierTypeCode = "Y;F;T;P";

    /** 地址 */
    @JSONField(name = "corpAddress")
    private String address;

    /** 创建人 NC */
    @JSONField(serialize = false)
    private String creator = "NC";

    /** 创建时间 */
    @JSONField(serialize = false)
    private Date createDate = DateUtil.date();

    /** GUID */
    @JSONField(serialize = false)
    private String guid = IdUtil.randomUUID();

    /** 是否可用 */
    @JSONField(serialize = false)
    private Boolean usable = true;

    /** NC 供应商编码 */
    @JSONField(serialize = false)
    private String NCSupplierCode;







}
