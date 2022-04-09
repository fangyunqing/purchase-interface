package com.hf.project.cloth.handler.supplier;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.cloth.entity.Supplier;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractLockValidHandleData;
import com.hf.project.common.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 供应商新增
 * @Author fyq
 * @Date 2021/9/7 15:18
 **/

@Component("supplierInsert")
public class SupplierInsertHandler extends AbstractLockValidHandleData<CommonInsert<List<Supplier>>> {

    @Autowired
    private SupplierService<Supplier> supplierService;

    @Override
    protected CommonInsert<List<Supplier>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<Supplier>>>(){});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<Supplier>> listCommonInsert) throws InterFaceException {

        reentrantLock.lock();
        try {
            supplierService.insertBatch(listCommonInsert.getData());
            return new CommonOutput(1,"");
        }
        finally {
            reentrantLock.unlock();
        }
    }
}
