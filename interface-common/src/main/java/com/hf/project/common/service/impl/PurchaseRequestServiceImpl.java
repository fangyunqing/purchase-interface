package com.hf.project.common.service.impl;

import com.hf.project.common.entity.upload.PurchaseRequest;
import com.hf.project.common.mapper.common.PurchaseRequestMapper;
import com.hf.project.common.service.PurchaseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 采购申请服务
 * @Author fyq
 * @Date 2021/8/26 14:12
 **/

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;

    @Override
    public List<PurchaseRequest> query(List<String> codes) {
        return purchaseRequestMapper.query(codes);
    }

    @Override
    public Boolean existRequestDlt(String billCode, String id, Boolean ifNotExistThrowException) {

        Boolean existed = purchaseRequestMapper.existRequestDlt(billCode, id);
        if (existed) {
            return true;
        }
        else {
            return false;
        }

    }
}

