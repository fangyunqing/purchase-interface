package com.hf.project.common.entity.enums;

public enum ActionName implements INumberEnum {

    INSERT(0, "新增接口"),
    DISABLE(1, "失效接口"),
    DELETE(2,"删除接口");

    ActionName(int code, String description) {

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
