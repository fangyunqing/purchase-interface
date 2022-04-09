package com.hf.project.common.handler;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 验证数据加锁
 * @Author fyq
 * @Date 2021/9/7 17:23
 **/

public abstract class AbstractLockValidHandleData<T> extends AbstractValidIHandleData<T> {

    /** 悲观锁 */
    protected ReentrantLock reentrantLock = new ReentrantLock();

}
