package com.hf.project.common.service;

import com.hf.project.common.entity.upload.PurchaseRequest;

import java.util.List;

public interface PurchaseRequestService {

    List<PurchaseRequest> query(List<String> codes);

    Boolean existRequestDlt(String billCode, String id, Boolean IfNullThrowException);
}
