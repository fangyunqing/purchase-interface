package com.hf.project.common.entity.enums;

public enum BaseDataType implements INumberEnum {

    CURRENCY(1, "币种"),
    INVOICE_TYPE(2, "发票类型"),
    PAYMENT_MODE(3, "付款方式"),
    PRICE_TERM(4, "价格条款"),
    MATERIAL_UNIT(5, "物料单位"),
    CONTRACT_TYPE(6, "采购类型"),
    DELIVERY_TYPE(7, "交货方式");

    BaseDataType(int code, String description) {

        this.code = code;

        this.description = description;

    }

    private final int code;

    private final String description;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
