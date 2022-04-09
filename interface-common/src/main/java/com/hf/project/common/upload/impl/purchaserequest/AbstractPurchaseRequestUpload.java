package com.hf.project.common.upload.impl.purchaserequest;

import cn.hutool.core.util.ObjectUtil;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.upload.PurchaseRequest;
import com.hf.project.common.service.PurchaseRequestService;
import com.hf.project.common.upload.AbstractUpLoad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 采购申请抽象类
 * @Author fyq
 * @Date 2021/8/30 14:08
 **/

public abstract class AbstractPurchaseRequestUpload extends AbstractUpLoad<PurchaseRequest> {

    @Autowired
    protected PurchaseRequestService purchaseRequestService;

    @Override
    protected List<PurchaseRequest> queryDataBase(List<String> codes) {
        List<PurchaseRequest> purchaseRequestList = purchaseRequestService.query(codes);
        // 分组
        Map<String, List<PurchaseRequest>> listMap = purchaseRequestList.stream().collect(Collectors.groupingBy(PurchaseRequest::getBillCode));
        for (Map.Entry<String, List<PurchaseRequest>> entry : listMap.entrySet()) {
            List<PurchaseRequest> list = entry.getValue().stream()
                    .sorted(Comparator.comparing(PurchaseRequest::getItemCode, Comparator.nullsFirst(Comparator.naturalOrder()))).
                            collect(Collectors.toList());
            for (int index = 0;index < list.size();index++) {

                PurchaseRequest purchaseRequest = list.get(index);

                String itemCode = String.format("%03d", index + 1);
                if (ObjectUtil.isNull(purchaseRequest.getItemCode()) || !itemCode.equals(purchaseRequest.getItemCode())) {
                    purchaseRequest.setItemCode(itemCode);
                }
            }
        }

        return purchaseRequestList;
    }

    @Override
    protected String getUploadName() {
        return BillCodeType.PURCHASE_REQUEST.getDescription();
    }

    @Override
    protected List<String> getBillCodes(List<PurchaseRequest> list) {
        return list.stream().map(PurchaseRequest::getBillCode).distinct().collect(Collectors.toList());
    }
}
