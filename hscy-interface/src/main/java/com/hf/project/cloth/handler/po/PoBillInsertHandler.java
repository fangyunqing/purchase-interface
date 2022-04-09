package com.hf.project.cloth.handler.po;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.cloth.entity.po.PoBillMaster;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractLockValidHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 订单插入
 * @Author fyq
 * @Date 2021/9/8 13:32
 **/

@Component("purchaseOrderInsert")
public class PoBillInsertHandler extends AbstractLockValidHandleData<CommonInsert<List<PoBillMaster>>> {

    @Autowired
    private PurchaseOrderService<PoBillMaster> purchaseOrderService;

    @Override
    protected CommonInsert<List<PoBillMaster>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<PoBillMaster>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<PoBillMaster>> listCommonInsert) throws InterFaceException {

        try {
            reentrantLock.lock();

            purchaseOrderService.insertBatch(listCommonInsert.getData());
            return new CommonOutput(1, "");
        }
        finally {
            reentrantLock.unlock();
        }

    }
}
