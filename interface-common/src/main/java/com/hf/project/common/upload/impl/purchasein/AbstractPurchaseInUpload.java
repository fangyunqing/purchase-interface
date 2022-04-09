package com.hf.project.common.upload.impl.purchasein;

import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr;
import com.hf.project.common.service.PurchaseInService;
import com.hf.project.common.upload.AbstractUpLoad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 入库单抽象类
 * @Author fyq
 * @Date 2021/8/30 14:01
 **/

public abstract class AbstractPurchaseInUpload extends AbstractUpLoad<PurchaseInHdr> {

    @Autowired
    protected PurchaseInService purchaseInService;

    @Override
    protected List<PurchaseInHdr> queryDataBase(List<String> codes) {
        return purchaseInService.query(codes);
    }

    @Override
    protected String getUploadName() {
        return BillCodeType.PURCHASE_IN.getDescription();
    }

    @Override
    protected List<String> getBillCodes(List<PurchaseInHdr> list) {
        return list.stream().map(PurchaseInHdr::getBillCode).distinct().collect(Collectors.toList());
    }
}
