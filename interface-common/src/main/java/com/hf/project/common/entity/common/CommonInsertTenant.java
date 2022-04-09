package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 入参带租户ID
 * @Author fyq
 * @Date 2021/6/4 9:37
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CommonInsertTenant<T> extends CommonParameterTenant {

    @JSONField(name = "datas")
    @NotNull(message = "数据不能为空")
    @Valid
    private T data;
}
