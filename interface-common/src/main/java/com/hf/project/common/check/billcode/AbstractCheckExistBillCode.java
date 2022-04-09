package com.hf.project.common.check.billcode;

import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.NoSuchBillCodeException;

/**
 * @Description 业务单据是否存在
 * @Author fyq
 * @Date 2021/11/23 8:59
 **/

public abstract class AbstractCheckExistBillCode<T> extends AbstractCheckBillCode<T> {

    @Override
    protected void throwException(String... args) throws InterFaceException {

        String billCode = args[0];
        String id = args[1];

        throw new NoSuchBillCodeException(requiredProperties.getSysName(),
                getBillCodeType().getDescription(),
                billCode,
                id);
    }
}
