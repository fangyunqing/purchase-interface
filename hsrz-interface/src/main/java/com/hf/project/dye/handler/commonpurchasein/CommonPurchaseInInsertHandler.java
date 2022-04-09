package com.hf.project.dye.handler.commonpurchasein;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 到货单插入
 * @Author fyq
 * @Date 2021/9/1 10:12
 **/

@Component("purchaseArrivalInsert")
public class CommonPurchaseInInsertHandler extends AbstractValidIHandleData<CommonInsert<List<CommonPurchaseInHdr>>> {

    @Autowired
    private PurchaseArrivalService<CommonPurchaseInHdr> purchaseArrivalService;

    @Override
    protected CommonInsert<List<CommonPurchaseInHdr>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonInsert<List<CommonPurchaseInHdr>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<CommonPurchaseInHdr>> listCommonInsert) throws InterFaceException {

        purchaseArrivalService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");
    }
}
