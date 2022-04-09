package com.hf.project.common.entity.common;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description 常规参数
 * @Author fyq
 * @Date 2021/5/7 13:37
 **/


@Data
class CommonParameter implements Serializable {

    @JSONField(name = "syscode")
    @NotBlank(message = "系统编码不能为空")
    private String sysCode;

    @JSONField(name = "orgname")
    @NotBlank(message = "组织名称不能为空")
    private String orgName;


}
