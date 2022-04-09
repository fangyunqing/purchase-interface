package com.hf.project.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Description 类型不存在
 * @Author fyq
 * @Date 2021/6/21 11:54
 **/

public class NoSuchTypeException extends InterFaceException {

    public  NoSuchTypeException(String sysName, String typeName, String typeNames){

        super(StrUtil.format("{} [{}]在列表{}不存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(typeName, "未知基础类型"),
                StrUtil.blankToDefault(typeNames, "未知全部基础类型")));


    }

}
