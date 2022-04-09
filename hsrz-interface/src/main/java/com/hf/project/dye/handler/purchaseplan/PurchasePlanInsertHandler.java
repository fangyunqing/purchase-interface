package com.hf.project.dye.handler.purchaseplan;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购订单插入
 * @Author fyq
 * @Date 2021/9/1 9:47
 **/

@Component("purchaseOrderInsert")
public class PurchasePlanInsertHandler extends AbstractValidIHandleData<CommonInsert<List<PurchasePlanHdr>>> {

    @Autowired
    private PurchaseOrderService<PurchasePlanHdr> purchaseOrderService;

    @Override
    protected CommonInsert<List<PurchasePlanHdr>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<PurchasePlanHdr>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<PurchasePlanHdr>> listCommonInsert) throws InterFaceException {

        purchaseOrderService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");
    }
}
