package com.hf.project.common.check.billcode;

import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.mapper.common.PurchaseRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测需求是否存在
 * @Author fyq
 * @Date 2021/11/23 9:09
 **/

@Service
public class CheckExistRequestService extends AbstractCheckExistBillCode<Boolean> {

    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];
        String id = args[1];

        Boolean existRequest = purchaseRequestMapper.existRequestDlt(billCode, id);
        if (!existRequest) {
            return null;
        }

        return true;

    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_REQUEST;
    }
}
