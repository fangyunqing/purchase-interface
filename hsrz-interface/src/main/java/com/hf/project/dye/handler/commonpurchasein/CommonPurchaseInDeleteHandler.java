package com.hf.project.dye.handler.commonpurchasein;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.dye.entity.commonpurchasein.CommonPurchaseInHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购到货
 * @Author fyq
 * @Date 2021/9/1 10:22
 **/

@Component("purchaseArrivalDelete")
public class CommonPurchaseInDeleteHandler extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseArrivalService<CommonPurchaseInHdr> purchaseArrivalService;

    @Override
    protected CommonSearch<List<String>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonSearch<List<String>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonSearch<List<String>> listCommonSearch) throws InterFaceException {

        purchaseArrivalService.deleteBatch(listCommonSearch.getData());

        return new CommonOutput(1,"");
    }
}
