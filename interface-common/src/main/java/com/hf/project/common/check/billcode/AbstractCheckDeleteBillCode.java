package com.hf.project.common.check.billcode;

import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.DeleteBillCodeErrorException;

/**
 * @Description 删除业务单
 * @Author fyq
 * @Date 2021/11/23 9:00
 **/

public abstract class AbstractCheckDeleteBillCode<T> extends AbstractCheckBillCode<T> {

    @Override
    protected void throwException(String... args) throws InterFaceException {

        String billCode = args[0];
        String reason = args[1];

        throw new DeleteBillCodeErrorException(requiredProperties.getSysName(),
                getBillCodeType().getDescription(),
                billCode,
                reason);

    }
}
