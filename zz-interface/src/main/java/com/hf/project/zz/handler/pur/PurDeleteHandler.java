package com.hf.project.zz.handler.pur;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseOrderService;
import com.hf.project.zz.entity.pur.PurHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购订单删除
 * @Author fyq
 * @Date 2021/10/13 10:00
 **/

@Component("purchaseOrderDelete")
public class PurDeleteHandler extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseOrderService<PurHdr> purchaseOrderService;

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
