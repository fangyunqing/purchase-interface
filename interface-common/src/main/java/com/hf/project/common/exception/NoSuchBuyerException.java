package com.hf.project.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Description 没有采购员
 * @Author fyq
 * @Date 2021/5/12 11:40
 **/

public class NoSuchBuyerException extends InterFaceException implements SendMail {

    public NoSuchBuyerException(String sysName, String buyerNo){

        super(StrUtil.format("{} 采购员[{}]在采购员列表中不存在",
                StrUtil.blankToDefault(sysName,"未知系统"),
                StrUtil.blankToDefault(buyerNo, "未知采购员")));
    }

}
