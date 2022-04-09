package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Description 入参 查询
 * @Author fyq
 * @Date 2021/5/7 13:57
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CommonSearch<T> extends CommonParameter {

    @JSONField(name = "billcodes")
    @NotNull(message = "查询条件不能为空")
    private T data;

    public CommonSearch(){
        this(null);
    }
}
