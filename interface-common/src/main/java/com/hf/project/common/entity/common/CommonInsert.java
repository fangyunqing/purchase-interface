package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 入参 插入
 * @Author fyq
 * @Date 2021/5/7 13:57
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CommonInsert<T> extends CommonParameter {

    @JSONField(name = "datas")
    @NotNull(message = "数据不能为空")
    @Valid
    private T data;

    public CommonInsert(){
        this(null);
    }
}
