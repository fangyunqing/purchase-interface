package com.hf.project.dye.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckDeleteBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.dye.mapper.CommonPurchaseInMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测到货单是否删除
 * @Author fyq
 * @Date 2021/11/24 11:12
 **/

@Service
public class CheckDeleteCommonPurchaseInService extends AbstractCheckDeleteBillCode<Boolean> {

    @Autowired
    private CommonPurchaseInMapper commonPurchaseInMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];
        Boolean canDelete = commonPurchaseInMapper.canDelete(billCode);
        if (!canDelete) {
            return null;
        }

        return true;
    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_ARRIVAL;
    }
}
