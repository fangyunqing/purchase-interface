package com.hf.project.zz.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckDeleteBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.zz.mapper.zz.SendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购到货是否可以删除
 * @Author fyq
 * @Date 2021/11/24 14:16
 **/

@Service
public class CheckDeleteSendService extends AbstractCheckDeleteBillCode<Boolean> {

    @Autowired
    private SendMapper sendMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];
        Boolean canDelete = sendMapper.canDelete(billCode);
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
