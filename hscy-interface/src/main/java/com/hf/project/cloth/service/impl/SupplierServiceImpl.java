package com.hf.project.cloth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.cloth.entity.Supplier;
import com.hf.project.cloth.mapper.SupplierMapper;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.DuplicateBillCodeException;
import com.hf.project.common.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 供应商服务类
 * @Author fyq
 * @Date 2021/9/7 15:21
 **/

@Service
public class SupplierServiceImpl implements SupplierService<Supplier> {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<Supplier> list) throws InterFaceException {

        List<String> billCodes =
                list.stream()
                        .map(Supplier::getSupplierCode)
                        .distinct()
                        .collect(Collectors.toList());

        // 重复
        if (billCodes.size() != list.size()) {
            throw new DuplicateBillCodeException(requiredProperties.getSysName(),  BillCodeType.SUPPLIER.getDescription(), StrUtil.join(",",billCodes));
        }



        Integer maxId = supplierMapper.getMaxId();
        for (Supplier supplier : list) {

            supplier.setNCSupplierCode(supplier.getSupplierCode());
            if (ObjectUtil.isNotNull(supplierMapper.querySingle(supplier.getNCSupplierCode()))) {
                supplierMapper.update(supplier);
            }
            else {
                supplier.setId(++maxId);
                supplierMapper.insert(supplier);
            }
        }

    }
}
