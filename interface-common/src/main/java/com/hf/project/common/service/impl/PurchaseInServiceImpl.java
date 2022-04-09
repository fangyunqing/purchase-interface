package com.hf.project.common.service.impl;

import com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr;
import com.hf.project.common.mapper.common.PurchaseInMapper;
import com.hf.project.common.service.PurchaseInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 入库服务类
 * @Author fyq
 * @Date 2021/8/26 15:51
 **/

@Service
public class PurchaseInServiceImpl implements PurchaseInService {

    @Autowired
    private PurchaseInMapper purchaseInMapper;

    @Override
    public List<PurchaseInHdr> query(List<String> codes) {
        return purchaseInMapper.query(codes);
    }

}
