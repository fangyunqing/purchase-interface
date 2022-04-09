package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 单据号存在重复
 * @Author fyq
 * @Date 2021/9/2 11:07
 **/

public class DuplicateBillCodeException extends InterFaceException {

    public DuplicateBillCodeException(String sysName, String type, String billCodes) {
        super(StrUtil.format("{}-{}[{}]存在重复",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(billCodes, "未知单据")));
    }

}
