package com.hf.project.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Description 基础档案不存在
 * @Author fyq
 * @Date 2021/9/8 11:24
 **/

public class NoSuchBaseDataException extends InterFaceException {

    public NoSuchBaseDataException(String sysName, String baseDataType, String code) {
        super(StrUtil.format("{}-基础档案[{}]中编码({})不存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(baseDataType, "未知基础档案"),
                StrUtil.blankToDefault(code, "未知编码")));
    }
}
