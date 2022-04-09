package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 单据号删除错误
 * @Author fyq
 * @Date 2021/8/31 11:53
 **/

public class DeleteBillCodeErrorException extends InterFaceException {

    public DeleteBillCodeErrorException(String sysName, String type, String billCodes, String reason) {
        super(StrUtil.format("{}-{}[{}]无法删除:{}",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(billCodes, "未知单据"),
                StrUtil.blankToDefault(reason, "未知原因")));
    }

}
