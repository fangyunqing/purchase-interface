package com.hf.project.common.entity.enums;

public enum UserRole implements INumberEnum {

    CREATOR(0, "创建人"),
    CONFIRM(1, "审核人"),
    BUYER(2, "采购员");


    UserRole(int code, String description) {

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
