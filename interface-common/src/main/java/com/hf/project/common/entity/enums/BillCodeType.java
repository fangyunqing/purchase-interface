package com.hf.project.common.entity.enums;

public enum  BillCodeType implements INumberEnum {

    MATERIAL(1, "物料"),
    PURCHASE_REQUEST(2,"采购申请"),
    PURCHASE_IN(3,"采购入库"),
    SUPPLIER(4, "供应商"),
    PURCHASE_CONTRACT(5,"采购合同"),
    PURCHASE_ORDER(6,"采购订单"),
    PURCHASE_ARRIVAL(7,"采购到货");

    BillCodeType(int code, String description) {

        this.code = code;

        this.description = description;

    }

    private int code;

    private String description;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
