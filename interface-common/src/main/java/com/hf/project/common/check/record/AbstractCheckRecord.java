package com.hf.project.common.check.record;

import com.hf.project.common.check.AbstractCheck;
import com.hf.project.common.entity.enums.RecordType;
import com.hf.project.common.exception.DiscordException;
import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 档案检测
 * @Author fyq
 * @Date 2021/11/23 8:24
 **/

public abstract class AbstractCheckRecord<T> extends AbstractCheck<T> {

    protected abstract RecordType getRecordType();

    @Override
    protected void throwException(String... args) throws InterFaceException {

        throw new DiscordException(requiredProperties.getSysName(),
                getRecordType().getDescription(),
                args[0]);

    }
}
