package com.hf.project.common.mapper.common;

import com.hf.project.common.entity.upload.PurchaseRequest;

import java.util.List;

public interface PurchaseRequestMapper {

    /**
     * @Author fyq
     * @Description 根据采购单号返回数据
     * @Date 14:09 2021/8/30
     * @Param [billCOdes]
     * @return java.util.List<com.hf.project.common.entity.upload.PurchaseRequest>
     **/
    List<PurchaseRequest> query(List<String> billCodes);

    /**
     * @Author fyq
     * @Description 采购明细是否存在
     * @Date 9:41 2021/9/3
     * @Param [billCode, itemCode]
     * @return java.lang.Boolean
     **/
    Boolean existRequestDlt(String billCode, String id);

}
