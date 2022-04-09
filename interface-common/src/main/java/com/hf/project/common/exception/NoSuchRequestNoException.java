package com.hf.project.common.exception;

/**
 * @Description 没有相应的采购申请单号
 * @Author fyq
 * @Date 2021/5/10 8:52
 **/

public class NoSuchRequestNoException extends InterFaceException {

    public NoSuchRequestNoException(String requestNo, Integer orderNo){
        super("采购申请[" + requestNo + "]中行号[" + orderNo + "]不存在");
    }
}
