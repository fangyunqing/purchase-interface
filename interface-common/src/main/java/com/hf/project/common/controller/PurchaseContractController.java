package com.hf.project.common.controller;

import com.hf.project.common.entity.common.CommonOutput;
import com.hf.project.common.handler.IHandlerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 采购合同
 * @Author fyq
 * @Date 2021/9/10 16:12
 **/

@RestController
@RequestMapping("/purchaseContract")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "purchase-contract", havingValue = "true",matchIfMissing = true)
public class PurchaseContractController {

    @Autowired
    @Qualifier("purchaseContractInsert")
    private IHandlerData insert;

    @Autowired
    @Qualifier("purchaseContractDelete")
    private IHandlerData delete;

    @PostMapping("/insert")
    public CommonOutput purchaseOrderInsert(@RequestBody String json) {
        return insert.handleData(json);
    }

    @PostMapping("/delete")
    public CommonOutput purchaseOrderDelete(@RequestBody String json) {
        return delete.handleData(json);
    }

}
