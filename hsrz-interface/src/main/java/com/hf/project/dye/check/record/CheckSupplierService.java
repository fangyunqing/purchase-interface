package com.hf.project.dye.check.record;

import com.hf.project.common.check.record.AbstractCheckRecord;
import com.hf.project.common.entity.enums.RecordType;
import com.hf.project.dye.entity.Provider;
import com.hf.project.dye.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 检测供应商
 * @Author fyq
 * @Date 2021/11/23 9:32
 **/

@Service
public class CheckSupplierService extends AbstractCheckRecord<Provider> {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    protected Provider doCheck(String... args) {

        String supplierNo = args[0];
        return providerMapper.querySingle(supplierNo);

    }

    @Override
    protected RecordType getRecordType() {
        return RecordType.PROVIDER;
    }
}
