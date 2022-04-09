package com.hf.project.zz.handler.send;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractLockValidHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.zz.entity.send.SendHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 到货单插入
 * @Author fyq
 * @Date 2021/10/14 13:05
 **/

@Component("purchaseArrivalInsert")
public class SendInsertHandler extends AbstractLockValidHandleData<CommonInsert<List<SendHead>>> {

    @Autowired
    private PurchaseArrivalService<SendHead> purchaseArrivalService;

    @Override
    protected CommonInsert<List<SendHead>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<SendHead>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<SendHead>> listCommonInsert) throws InterFaceException {

        reentrantLock.lock();
        try {
            purchaseArrivalService.insertBatch(listCommonInsert.getData());
            return new CommonOutput(1,"");
        }
        finally {
            reentrantLock.unlock();
        }


    }
}
