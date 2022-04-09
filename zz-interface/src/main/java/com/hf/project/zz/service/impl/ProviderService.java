package com.hf.project.zz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.DuplicateBillCodeException;
import com.hf.project.common.service.SupplierService;
import com.hf.project.zz.entity.Provider;
import com.hf.project.zz.mapper.zz.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 供应商服务类
 * @Author fyq
 * @Date 2021/10/11 14:45
 **/

@Service
public class ProviderService implements SupplierService<Provider> {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "ZZTransactionManager")
    public void insertBatch(List<Provider> list) throws InterFaceException {
        List<String> billCodes =
                list.stream()
                        .map(Provider::getProviderNo)
                        .distinct()
                        .collect(Collectors.toList());

        // 重复
        if (billCodes.size() != list.size()) {
            throw new DuplicateBillCodeException(requiredProperties.getSysName(),  BillCodeType.SUPPLIER.getDescription(), StrUtil.join(",",billCodes));
        }

        // 插入
        for (Provider provider : list) {

            if (ObjectUtil.isNotNull(providerMapper.querySingle(provider.getProviderNo()))) {
                providerMapper.update(provider);
            }
            else {
                providerMapper.insert(provider);
            }

        }

    }
}
