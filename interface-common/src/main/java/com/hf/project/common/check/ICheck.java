package com.hf.project.common.check;

import com.hf.project.common.exception.InterFaceException;

public interface ICheck<T> {

    T check(String... args) throws InterFaceException;

}
