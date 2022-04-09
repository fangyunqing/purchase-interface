package com.hf.project.common.check.billcode;

import com.hf.project.common.check.AbstractCheck;
import com.hf.project.common.entity.enums.BillCodeType;

/**
 * @Description 检查业务单据
 * @Author fyq
 * @Date 2021/11/23 9:01
 **/

public abstract class AbstractCheckBillCode<T> extends AbstractCheck<T> {

    protected abstract BillCodeType getBillCodeType();

}
