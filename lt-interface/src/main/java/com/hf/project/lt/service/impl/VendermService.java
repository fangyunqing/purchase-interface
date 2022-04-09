package com.hf.project.lt.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.exception.billcodeexception.DuplicateBillCodeException;
import com.hf.project.common.service.SupplierService;
import com.hf.project.lt.entity.MatVenderm;
import com.hf.project.lt.mapper.MatVendermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 供应商服务类
 * @Author fyq
 * @Date 2021/9/20 15:01
 **/

@Service
public class VendermService implements SupplierService<MatVenderm> {

    @Autowired
    private MatVendermMapper matVendermMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Override
    public void insertBatch(List<MatVenderm> list) throws InterFaceException {

        List<String> billCodes =
                list.stream()
                        .map(MatVenderm::getVendermId)
                        .distinct()
                        .collect(Collectors.toList());

        // 重复
        if (billCodes.size() != list.size()) {
            throw new DuplicateBillCodeException(requiredProperties.getSysName(),  BillCodeType.SUPPLIER.getDescription(), StrUtil.join(",",billCodes));
        }

        // 插入
        for (MatVenderm matVenderm : list) {
            matVenderm.setNCVendermId(matVenderm.getVendermId());
            if (ObjectUtil.isNotNull(matVendermMapper.querySingle(matVenderm.getNCVendermId()))) {
                matVendermMapper.update(matVenderm);
            }
            else {
                matVendermMapper.insert(matVenderm);
            }

        }
    }
}
