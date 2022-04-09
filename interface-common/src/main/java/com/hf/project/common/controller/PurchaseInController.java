package com.hf.project.common.controller;

import com.hf.project.common.upload.impl.purchasein.PurchaseInDeleteUpload;
import com.hf.project.common.upload.impl.purchasein.PurchaseInInsertUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 入库单
 * @Author fyq
 * @Date 2021/8/31 17:02
 **/

@RestController
@RequestMapping("/purchaseIn")
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "purchase-in", havingValue = "true",matchIfMissing = true)
public class PurchaseInController {

    @Autowired
    private PurchaseInDeleteUpload purchaseInDeleteUpload;

    @Autowired
    private PurchaseInInsertUpload purchaseInInsertUpload;

    @GetMapping("/insert")
    public String purchaseInInsert(String codes, String workCode) {
        return purchaseInInsertUpload.upLoad(codes, workCode);
    }

    @GetMapping("/delete")
    public String purchaseInDelete(String codes, String workCode) {
        return purchaseInDeleteUpload.upLoad(codes, workCode);
    }


}
