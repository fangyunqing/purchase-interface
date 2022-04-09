package com.hf.project.dye.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckExistBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.dye.mapper.PurchasePlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购订单明细是否存在
 * @Author fyq
 * @Date 2021/11/24 10:14
 **/

@Service
public class CheckExistPurchasePlanService extends AbstractCheckExistBillCode<String> {

    @Autowired
    private PurchasePlanMapper purchasePlanMapper;

    @Override
    protected String doCheck(String... args) {

        String billCode = args[0];
        String id = args[1];

        return purchasePlanMapper.getDtlKey(billCode, id);
    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_ORDER;
    }
}
