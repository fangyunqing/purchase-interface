package com.hf.project.common.mapper.common;

import com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr;

import java.util.List;

public interface PurchaseInMapper {

    /**
     * @Author fyq
     * @Description 根据入库单号返回数据
     * @Date 14:03 2021/8/30
     * @Param [codes]
     * @return java.util.List<com.hf.project.common.entity.upload.PurchaseIn.PurchaseInHdr>
     **/
    List<PurchaseInHdr> query(List<String> codes);

}
