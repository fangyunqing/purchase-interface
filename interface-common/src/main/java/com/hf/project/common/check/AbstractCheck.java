package com.hf.project.common.check;

import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 抽检检查类
 * @Author fyq
 * @Date 2021/11/23 8:18
 **/

public abstract class AbstractCheck<T> implements ICheck<T> {

    @Autowired
    protected RequiredProperties requiredProperties;

    @Override
    public T check(String... args) throws InterFaceException {

        T t = doCheck(args);
        if (t == null) {
            throwException(args);
        }

        return t;
    }


    protected abstract T doCheck(String... args);

    protected abstract void throwException(String... args) throws InterFaceException;
}
