package com.hf.project.lt.entity;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Description 供应商
 * @Author fyq
 * @Date 2021/9/20 14:42
 **/

@Data
public class MatVenderm {

    /** 供应商编码 */
    @JSONField(name = "code")
    @NotBlank(message = "供应商编码不能为空")
    private String vendermId;

    /** 供应商名称 */
    @JSONField(name = "name")
    @NotBlank(message = "供应商名称不能为空")
    private String vendermName;

    /** 地址 */
    @JSONField(name = "corpAddress")
    private String address;

    /** NC 供应商编码 */
    @JSONField(deserialize = false)
    private String NCVendermId;

    /** 创建人 NC */
    @JSONField(deserialize = false)
    private String creator = "NC";

    /** 创建时间 */
    @JSONField(deserialize = false)
    private Date createTime = DateUtil.date();






}
