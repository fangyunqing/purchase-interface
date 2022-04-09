package com.hf.project.cloth.check.billcode;

import com.hf.project.cloth.mapper.PoBillMapper;
import com.hf.project.common.check.billcode.AbstractCheckDeleteBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购订单是否可以删除
 * @Author fyq
 * @Date 2021/11/23 10:05
 **/

@Service
public class CheckDeletePoBillService extends AbstractCheckDeleteBillCode<Boolean> {

    @Autowired
    private PoBillMapper poBillMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];
        Boolean canDelete = poBillMapper.canDelete(billCode);

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
