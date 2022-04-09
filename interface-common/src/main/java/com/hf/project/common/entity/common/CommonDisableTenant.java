package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author fyq
 * @Date 2021/9/2 11:31
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CommonDisableTenant<T> extends CommonParameterTenant{

    @JSONField(name = "codes")
    @NotNull(message = "数据不能为空")
    @Valid
    private T data;

}
