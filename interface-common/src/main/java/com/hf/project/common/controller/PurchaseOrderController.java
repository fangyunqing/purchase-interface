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
 * @Description 订单
 * @Author fyq
 * @Date 2021/9/8 13:37
 **/

@RestController
@RequestMapping("/purchaseOrder")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "purchase-order", havingValue = "true",matchIfMissing = true)
public class PurchaseOrderController {

    @Autowired
    @Qualifier("purchaseOrderInsert")
    private IHandlerData orderInsert;

    @Autowired
    @Qualifier("purchaseOrderDelete")
    private IHandlerData orderDelete;

    @PostMapping("/insert")
    public CommonOutput purchaseOrderInsert(@RequestBody String json) {
        return orderInsert.handleData(json);
    }

    @PostMapping("/delete")
    public CommonOutput purchaseOrderDelete(@RequestBody String json) {
        return orderDelete.handleData(json);
    }
}
