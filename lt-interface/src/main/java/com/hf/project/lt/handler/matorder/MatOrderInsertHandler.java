package com.hf.project.lt.handler.matorder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.lt.entity.matorder.MatOrderHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购订单
 * @Author fyq
 * @Date 2021/9/23 14:02
 **/

@Component("purchaseOrderInsert")
public class MatOrderInsertHandler extends AbstractValidIHandleData<CommonInsert<List<MatOrderHdr>>> {

    @Autowired
    private PurchaseOrderService<MatOrderHdr> purchaseOrderService;

    @Override
    protected CommonInsert<List<MatOrderHdr>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<MatOrderHdr>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<MatOrderHdr>> listCommonInsert) throws InterFaceException {
        purchaseOrderService.insertBatch(listCommonInsert.getData());
        return new CommonOutput(1, "");
    }
}
