package com.hf.project.cloth.check.billcode;

import com.hf.project.cloth.mapper.PoBillMapper;
import com.hf.project.common.check.billcode.AbstractCheckExistBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购订单是否存在
 * @Author fyq
 * @Date 2021/11/23 10:13
 **/

@Service
public class CheckExistPoBillService extends AbstractCheckExistBillCode<String> {

    @Autowired
    private PoBillMapper poBillMapper;

    @Override
    protected String doCheck(String... args) {

        String billCode = args[0];
        String id = args[1];

        return poBillMapper.getDtlKey(billCode, id);

    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_ORDER;
    }
}
