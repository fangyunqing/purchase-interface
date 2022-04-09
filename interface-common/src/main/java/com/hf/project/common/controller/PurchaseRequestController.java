package com.hf.project.common.controller;

import com.hf.project.common.upload.impl.purchaserequest.PurchaseRequestDeleteUpload;
import com.hf.project.common.upload.impl.purchaserequest.PurchaseRequestInsertUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 采购需求
 * @Author fyq
 * @Date 2021/8/31 16:56
 **/

@RestController
@RequestMapping("/purchaseRequest")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "purchase-request", havingValue = "true",matchIfMissing = true)
public class PurchaseRequestController {

    @Autowired
    private PurchaseRequestDeleteUpload purchaseRequestDeleteUpload;

    @Autowired
    private PurchaseRequestInsertUpload purchaseRequestInsertUpload;

    @GetMapping("/insert")
    public String purchaseRequestInsert(String codes, String workCode) {
        return purchaseRequestInsertUpload.upLoad(codes, workCode);
    }

    @GetMapping("/delete")
    public String purchaseRequestDelete(String codes, String workCode) {
        return purchaseRequestDeleteUpload.upLoad(codes, workCode);
    }

}
