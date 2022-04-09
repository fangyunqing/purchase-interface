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
 * @Description 到货
 * @Author fyq
 * @Date 2021/9/9 8:19
 **/

@RestController
@RequestMapping("purchaseArrival")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "purchase-arrival", havingValue = "true",matchIfMissing = true)
public class PurchaseArrivalController {

    @Autowired
    @Qualifier("purchaseArrivalInsert")
    private IHandlerData insert;

    @Autowired
    @Qualifier("purchaseArrivalDelete")
    private IHandlerData delete;

    @PostMapping("/insert")
    public CommonOutput purchaseArrivalInsert(@RequestBody String json) {
        return insert.handleData(json);
    }

    @PostMapping("/delete")
    public CommonOutput purchaseArrivalDelete(@RequestBody String json) {
        return delete.handleData(json);
    }
}
