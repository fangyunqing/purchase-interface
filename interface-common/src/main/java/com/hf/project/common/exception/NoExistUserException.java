package com.hf.project.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Description 用户不存在
 * @Author fyq
 * @Date 2021/9/24 16:08
 **/

public class NoExistUserException extends InterFaceException {

    public NoExistUserException(String sysName, String workCode, String role) {
        super(StrUtil.format("{}-{}[{}]不存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(role, "未知角色"),
                StrUtil.blankToDefault(workCode, "未知工号")));
    }

}
