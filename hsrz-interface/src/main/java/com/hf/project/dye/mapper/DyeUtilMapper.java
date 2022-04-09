package com.hf.project.dye.mapper;

import com.hf.project.dye.entity.MaterialType;
import com.hf.project.dye.entity.purchaseplan.PurchasePlanHdr;

import java.util.List;

public interface DyeUtilMapper {

    List<MaterialType> queryMaterialTypeFatherTree(String id);

    List<MaterialType> queryMaterialTypeChildTree(String id);

    PurchasePlanHdr queryPurchasePlanByPlanNo(String planNo);
}
