package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 单据号已经存在
 * @Author fyq
 * @Date 2021/8/31 11:50
 **/

public class ExistBillCodeException extends InterFaceException {

    public ExistBillCodeException(String sysName, String type, String billCodes) {
        super(StrUtil.format("{}-{}[{}]已经存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(billCodes, "未知单据")));
    }
}
