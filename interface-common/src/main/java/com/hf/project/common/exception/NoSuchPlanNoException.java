package com.hf.project.common.exception;

/**
 * @Description 采购订单不存在
 * @Author fyq
 * @Date 2021/5/10 8:55
 **/

public class NoSuchPlanNoException extends InterFaceException {

    public NoSuchPlanNoException(String planNo, Integer orderNo) {
        super("采购订单[" + planNo + "]中行号[" + orderNo + "]不存在");
    }
}
