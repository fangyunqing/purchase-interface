package com.hf.project.zz.check.billcode;

import com.hf.project.common.check.billcode.AbstractCheckExistBillCode;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.zz.entity.pur.PurDtl;
import com.hf.project.zz.mapper.zz.PurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测采购订单是否存在
 * @Author fyq
 * @Date 2021/11/24 14:08
 **/

@Service
public class CheckExistPurService extends AbstractCheckExistBillCode<PurDtl> {

    @Autowired
    private PurMapper purMapper;

    @Override
    protected PurDtl doCheck(String... args) {

        String billCode = args[0];
        String id = args[1];
        return purMapper.getPurDtl(billCode, id);
    }

    @Override
    protected BillCodeType getBillCodeType() {
        return BillCodeType.PURCHASE_ORDER;
    }
}
