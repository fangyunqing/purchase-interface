package com.hf.project.common.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 出参
 * @Author fyq
 * @Date 2021/5/8 9:15
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonOutput<T> implements Serializable {

    @JSONField(name = "result")
    private Integer result;

    @JSONField(name = "errorMsg")
    private String errorMsg;

    @JSONField(name = "billcodes")
    private T billCodes;

    public CommonOutput(Integer result, String errorMsg) {
        this(result, errorMsg, null);
    }


}
