package com.hf.project.cloth.mapper;

import com.hf.project.cloth.entity.MRPRequest;
import com.hf.project.cloth.entity.OrderInfo;

public interface ClothUtilMapper {

    MRPRequest getMRPRequest(String requestDtlId);

    Integer getMaxCurrencyId();

    Integer getMaxInvoiceId();

    Integer getMaxUnitId();

    Integer getMaxPaymentId();

    OrderInfo getOrderInfo(String productNo);
}
