package com.hf.project.common.entity.enums;

/**
 * @Description 档案
 * @Author fyq
 * @Date 2021/11/23 8:26
 **/

public enum RecordType implements INumberEnum {

    MATERIAL(1, "物料档案"),
    USER(2, "用户档案"),
    PROVIDER(3, "供应商");

    RecordType(int code, String description) {

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
