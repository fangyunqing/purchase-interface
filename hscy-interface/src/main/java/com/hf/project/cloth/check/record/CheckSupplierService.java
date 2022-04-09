package com.hf.project.cloth.check.record;

import com.hf.project.cloth.entity.Supplier;
import com.hf.project.cloth.mapper.SupplierMapper;
import com.hf.project.common.check.record.AbstractCheckRecord;
import com.hf.project.common.entity.enums.RecordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测供应商
 * @Author fyq
 * @Date 2021/11/23 9:32
 **/

@Service
public class CheckSupplierService extends AbstractCheckRecord<Supplier> {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    protected Supplier doCheck(String... args) {

        String supplierNo = args[0];
        return supplierMapper.querySingle(supplierNo);

    }

    @Override
    protected RecordType getRecordType() {
        return RecordType.PROVIDER;
    }
}
