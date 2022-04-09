package com.hf.project.common.service;


import com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr;

import java.util.List;

public interface PurchaseInService {

    /**
     * @Author fyq
     * @Description 查询
     * @Date 16:11 2021/9/3
     * @Param [codes]
     * @return java.util.List<com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr>
     **/
    List<PurchaseInHdr> query(List<String> codes);
}
