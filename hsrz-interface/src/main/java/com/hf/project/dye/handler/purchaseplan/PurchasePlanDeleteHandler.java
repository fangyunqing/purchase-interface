package com.hf.project.dye.handler.purchaseplan;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购订单删除
 * @Author fyq
 * @Date 2021/9/1 10:00
 **/

@Component("purchaseOrderDelete")
public class PurchasePlanDeleteHandler extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseOrderService<PurchasePlanHdr> purchaseOrderService;

    @Override
    protected CommonSearch<List<String>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonSearch<List<String>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonSearch<List<String>> listCommonSearch) throws InterFaceException {

        purchaseOrderService.deleteBatch(listCommonSearch.getData());

        return new CommonOutput(1,"");
    }
}
