package com.hf.project.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Description 字段要求
 * @Author fyq
 * @Date 2021/5/8 11:17
 **/

public class FieldRequireException extends InterFaceException {

    public FieldRequireException(String sysName, String message){
        super(StrUtil.format("{}-{}",
                StrUtil.blankToDefault(sysName,"未知系统"),
                StrUtil.blankToDefault(message, "空")));
    }

}
