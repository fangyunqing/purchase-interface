package com.hf.project.zz.handler.send;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseArrivalService;
import com.hf.project.zz.entity.send.SendHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 到货单删除
 * @Author fyq
 * @Date 2021/10/14 13:08
 **/

@Component("purchaseArrivalDelete")
public class SendDeleteHandler  extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseArrivalService<SendHead> purchaseArrivalService;

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
