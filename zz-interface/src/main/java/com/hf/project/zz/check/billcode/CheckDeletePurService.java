package com.hf.project.zz.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckDeleteBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.zz.mapper.zz.PurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测订单是否存在
 * @Author fyq
 * @Date 2021/11/24 13:59
 **/

@Service
public class CheckDeletePurService extends AbstractCheckDeleteBillCode<Boolean> {

    @Autowired
    private PurMapper purMapper;

    @Override
    protected Boolean doCheck(String... args) {

        String billCode = args[0];
        Boolean canDelete = purMapper.canDelete(billCode);
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
