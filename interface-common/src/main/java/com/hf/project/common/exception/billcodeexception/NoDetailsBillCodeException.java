package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 没有明细异常
 * @Author fyq
 * @Date 2021/6/17 9:43
 **/

public class NoDetailsBillCodeException extends InterFaceException {

    public NoDetailsBillCodeException(String sysName, String type, String billCode){
        super(StrUtil.format("{}-{}[{}]没有明细数据",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(billCode, "未知单据")));
    }
}
