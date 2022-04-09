package com.hf.project.common.upload.impl.purchaserequest;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.entity.upload.PurchaseRequest;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 采购申请删除
 * @Author fyq
 * @Date 2021/8/26 14:46
 **/

@Component
public class PurchaseRequestDeleteUpload extends AbstractPurchaseRequestUpload {


    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getDeletePurchaseReqUrl();
    }

    @Override
    protected Object getObject(List dataList) {
        return CommonObjectUtil.getCommonDeleteTenant(dataList, requiredProperties);
    }

    @Override
    protected ActionName getActionName() {
        return ActionName.DELETE;
    }

    @Override
    protected List filterData(List<PurchaseRequest> purchaseRequests) {
        return purchaseRequests.stream()
                .map(PurchaseRequest::getBillCode)
                .distinct()
                .collect(Collectors.toList());
    }
}
