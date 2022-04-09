package com.hf.project.common.upload.impl.purchasein;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 入库单新增
 * @Author fyq
 * @Date 2021/8/26 15:59
 **/

@Component
public class PurchaseInInsertUpload extends AbstractPurchaseInUpload {


    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getInsertPurchaseInUrl();
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
