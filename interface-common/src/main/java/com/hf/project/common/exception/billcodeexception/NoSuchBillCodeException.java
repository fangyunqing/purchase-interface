package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 单据号不存在
 * @Author fyq
 * @Date 2021/8/31 11:40
 **/

public class NoSuchBillCodeException extends InterFaceException {

    public NoSuchBillCodeException(String sysName, String type, String billCodes, String itemCode) {
        super(StrUtil.format("{}-{}[{}]中明细[{}]不存在",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(billCodes, "未知单据"),
                StrUtil.blankToDefault(itemCode, "未知明细")));
    }

}
