package com.hf.project.common.upload.impl.purchasein;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 入库单删除
 * @Author fyq
 * @Date 2021/8/26 16:46
 **/

@Component
public class PurchaseInDeleteUpload extends AbstractPurchaseInUpload {


    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getDeletePurchaseInUrl();
    }

    @Override
    protected Object getObject(List dataList) {
        return CommonObjectUtil.getCommonDeleteTenant(dataList, requiredProperties);
    }

    @Override
    protected ActionName getActionName() {
        return ActionName.DELETE;
    }

    protected List filterData(List<PurchaseInHdr> purchaseInHdrList) {
        return purchaseInHdrList.stream()
                .map(PurchaseInHdr::getBillCode)
                .distinct()
                .collect(Collectors.toList());
    }
}
