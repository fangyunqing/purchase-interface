package com.hf.project.cloth.handler.poin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.cloth.entity.poin.CommonPoBillInHdr;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractLockValidHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 入库新增
 * @Author fyq
 * @Date 2021/9/9 8:25
 **/

@Component("purchaseArrivalInsert")
public class PoBillInInsertHandler extends AbstractLockValidHandleData<CommonInsert<List<CommonPoBillInHdr>>> {

    @Autowired
    private PurchaseArrivalService<CommonPoBillInHdr> purchaseArrivalService;

    @Override
    protected CommonInsert<List<CommonPoBillInHdr>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<CommonPoBillInHdr>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<CommonPoBillInHdr>> listCommonInsert) throws InterFaceException {

        try {
            reentrantLock.lock();

            purchaseArrivalService.insertBatch(listCommonInsert.getData());
            return new CommonOutput(1, "");
        }
        finally {
            reentrantLock.unlock();
        }

    }
}
