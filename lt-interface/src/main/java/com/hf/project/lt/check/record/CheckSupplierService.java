package com.hf.project.lt.check.record;

import com.hf.project.common.check.record.AbstractCheckRecord;
import com.hf.project.common.entity.enums.RecordType;
import com.hf.project.lt.entity.MatVenderm;
import com.hf.project.lt.mapper.MatVendermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测供应商
 * @Author fyq
 * @Date 2021/11/23 9:32
 **/

@Service
public class CheckSupplierService extends AbstractCheckRecord<MatVenderm> {

    @Autowired
    private MatVendermMapper matVendermMapper;

    @Override
    protected MatVenderm doCheck(String... args) {

        String supplierNo = args[0];
        return matVendermMapper.querySingle(supplierNo);

    }

    @Override
    protected RecordType getRecordType() {
        return RecordType.PROVIDER;
    }
}
