package com.hf.project.zz.handler.pur;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.zz.entity.pur.PurHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购订单新增
 * @Author fyq
 * @Date 2021/10/13 9:56
 **/

@Component("purchaseOrderInsert")
public class PurInsertHandler extends AbstractValidIHandleData<CommonInsert<List<PurHdr>>> {

    @Autowired
    private PurchaseOrderService<PurHdr> purchaseOrderService;

    @Override
    protected CommonInsert<List<PurHdr>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<PurHdr>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<PurHdr>> listCommonInsert) throws InterFaceException {

        purchaseOrderService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");
    }
}
