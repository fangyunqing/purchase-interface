package com.hf.project.cloth.handler.poin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.cloth.entity.poin.CommonPoBillInHdr;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 入库删除
 * @Author fyq
 * @Date 2021/9/9 8:27
 **/

@Component("purchaseArrivalDelete")
public class PoBillInDeleteHandler extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseArrivalService<CommonPoBillInHdr> purchaseArrivalService;


    @Override
    protected CommonSearch<List<String>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonSearch<List<String>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonSearch<List<String>> listCommonSearch) throws InterFaceException {
        purchaseArrivalService.deleteBatch(listCommonSearch.getData());
        return new CommonOutput(1, "");
    }
}
