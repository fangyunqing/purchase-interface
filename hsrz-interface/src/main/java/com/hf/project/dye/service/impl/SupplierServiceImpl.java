package com.hf.project.dye.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.billcodeexception.DuplicateBillCodeException;
import com.hf.project.common.exception.billcodeexception.ExistBillCodeException;
import com.hf.project.common.service.SupplierService;
import com.hf.project.dye.entity.Provider;
import com.hf.project.dye.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 供应商服务实现类
 * @Author fyq
 * @Date 2021/8/27 10:06
 **/

@Service
public class SupplierServiceImpl implements SupplierService<Provider> {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<Provider> list) throws DuplicateBillCodeException, ExistBillCodeException {

        List<String> billCodes =
                list.stream()
                        .map(Provider::getProviderNo)
                        .distinct()
                        .collect(Collectors.toList());

        // 重复
        if (billCodes.size() != list.size()) {
            throw new DuplicateBillCodeException(requiredProperties.getSysName(), BillCodeType.SUPPLIER.getDescription(), StrUtil.join(",",billCodes));
        }

        // 插入
        for (Provider provider : list) {
            provider.setNCProviderNo(provider.getProviderNo());
            if (ObjectUtil.isNotNull(providerMapper.querySingle(provider.getNCProviderNo()))) {
                providerMapper.update(provider);
            }
            else {
                providerMapper.insert(provider);
            }
        }
    }
}
