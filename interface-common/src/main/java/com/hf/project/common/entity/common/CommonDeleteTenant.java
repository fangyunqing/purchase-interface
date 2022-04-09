package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 通过删除接口
 * @Author fyq
 * @Date 2021/9/2 13:03
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CommonDeleteTenant<T> extends CommonParameterTenant{

    @JSONField(name = "billcodes")
    @NotNull(message = "数据不能为空")
    @Valid
    private T data;

}
