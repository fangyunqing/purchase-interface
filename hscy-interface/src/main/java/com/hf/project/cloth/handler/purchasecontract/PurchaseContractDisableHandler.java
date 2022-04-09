package com.hf.project.cloth.handler.purchasecontract;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.cloth.entity.purchasecontract.PurchaseContractHdr;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.entity.common.CommonSearch;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购合同作废
 * @Author fyq
 * @Date 2021/9/4 9:31
 **/

@Component("purchaseContractDelete")
public class PurchaseContractDisableHandler extends AbstractValidIHandleData<CommonSearch<List<String>>> {

    @Autowired
    private PurchaseContractService<PurchaseContractHdr> purchaseContractService;

    @Override
    protected CommonSearch<List<String>> parseJson(String json) {
        return JSONObject.parseObject(json, new TypeReference<CommonSearch<List<String>>>() {});
    }

    @Override
    protected CommonOutput doHandleData(CommonSearch<List<String>> listCommonSearch) throws InterFaceException {

        purchaseContractService.disableBatch(listCommonSearch.getData());

        return new CommonOutput(1,"");
    }
}
