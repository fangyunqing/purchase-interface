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
 * @Description 供应商
 * @Author fyq
 * @Date 2021/9/8 13:51
 **/

@RestController
@RequestMapping("/supplier")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "supplier", havingValue = "true",matchIfMissing = true)
public class SupplierController {

    @Autowired
    @Qualifier("supplierInsert")
    private IHandlerData supplierInsert;

    @PostMapping("/insert")
    public CommonOutput supplierInsert(@RequestBody String json) {
        return supplierInsert.handleData(json);
    }
}
