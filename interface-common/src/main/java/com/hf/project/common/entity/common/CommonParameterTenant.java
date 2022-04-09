package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @Description 租户
 * @Author fyq
 * @Date 2021/9/2 11:29
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonParameterTenant extends CommonParameter{

    @JSONField(name = "tenantid")
    @NotBlank(message = "租户ID不能为空")
    private String tenantId;

}
