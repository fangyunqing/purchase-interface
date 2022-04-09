package com.hf.project.dye.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckDeleteBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.dye.mapper.PurchasePlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购订单是否可以删除
 * @Author fyq
 * @Date 2021/11/24 9:51
 **/

@Service
public class CheckDeletePurchasePlanService extends AbstractCheckDeleteBillCode<Boolean> {

    @Autowired
    private PurchasePlanMapper purchasePlanMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];

        Boolean canDelete = purchasePlanMapper.canDelete(billCode);
        if (!canDelete) {
            return null;
        }

        return true;
    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_ORDER;
    }
}
