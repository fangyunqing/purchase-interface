package com.hf.project.common.upload.impl.purchaserequest;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 采购申请新增
 * @Author fyq
 * @Date 2021/8/26 13:40
 **/

@Component
public class PurchaseRequestInsertUpload extends AbstractPurchaseRequestUpload {


    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getInsertPurchaseReqUrl();
    }

    @Override
    protected Object getObject(List dataList) {
        return CommonObjectUtil.getCommonInsertTenant(dataList, requiredProperties);
    }

    @Override
    protected ActionName getActionName() {
        return ActionName.INSERT;
    }
}
