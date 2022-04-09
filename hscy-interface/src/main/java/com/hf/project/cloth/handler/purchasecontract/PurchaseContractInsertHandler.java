package com.hf.project.cloth.handler.purchasecontract;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hf.project.common.entity.common.CommonInsert;
import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.handler.AbstractValidIHandleData;
import com.hf.project.common.service.PurchaseContractService;
import com.hf.project.cloth.entity.purchasecontract.PurchaseContractHdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购合同插入
 * @Author fyq
 * @Date 2021/9/4 9:31
 **/

@Component("purchaseContractInsert")
public class PurchaseContractInsertHandler extends AbstractValidIHandleData<CommonInsert<List<PurchaseContractHdr>>> {

    @Autowired
    private PurchaseContractService<PurchaseContractHdr> purchaseContractService;

    @Override
    protected CommonInsert<List<PurchaseContractHdr>> parseJson(String json) {

        CommonInsert<List<PurchaseContractHdr>> commonInsert = JSONObject.parseObject(json, new TypeReference<CommonInsert<List<PurchaseContractHdr>>>() {});

        commonInsert.getData().forEach(purchaseContractHdr -> {
            if (ObjectUtil.isNull(purchaseContractHdr.getCreator())) {
                purchaseContractHdr.setCreator("admin");
            }

            if (ObjectUtil.isNull(purchaseContractHdr.getCreateTime())) {
                purchaseContractHdr.setCreateTime(DateUtil.date());
            }

            if (ObjectUtil.isNull(purchaseContractHdr.getConfirmMan())) {
                purchaseContractHdr.setConfirmMan("admin");
            }

            if (ObjectUtil.isNull(purchaseContractHdr.getConfirmTime())) {
                purchaseContractHdr.setConfirmTime(DateUtil.date());
            }
        });

        return commonInsert;
    }

    @Override
    protected CommonOutput doHandleData(CommonInsert<List<PurchaseContractHdr>> listCommonInsert) throws InterFaceException {

        purchaseContractService.insertBatch(listCommonInsert.getData());

        return new CommonOutput(1,"");
    }
}
