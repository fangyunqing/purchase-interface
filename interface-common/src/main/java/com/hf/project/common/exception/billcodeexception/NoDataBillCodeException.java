package com.hf.project.common.exception.billcodeexception;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.exception.InterFaceException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 无法查询数据
 * @Author fyq
 * @Date 2021/8/30 9:41
 **/

public class NoDataBillCodeException extends InterFaceException {

    private ActionName actionName;

    private String type;

    private String sysName;

    private Set<String> codes = new HashSet<>();

    public void addCode(String code) {
        codes.add(code);
    }

    public void addAll(Collection<? extends String> collection) {
        codes.addAll(collection);
    }

    public int getCodeSize() {
        return codes.size();
    }

    public NoDataBillCodeException(String sysName, String type, ActionName actionName) {
        super("");
        this.actionName = actionName;
        this.type = type;
        this.sysName = sysName;
    }

    @Override
    public String getMessage() {
        return StrUtil.format("{}-{}[{}]未查询到数据({})",
                StrUtil.blankToDefault(sysName, "未知系统"),
                StrUtil.blankToDefault(type, "未知类型"),
                StrUtil.blankToDefault(StrUtil.join(",", codes), "未知单据"),
                StrUtil.blankToDefault(actionName.getDescription(), "未知操作"));
    }
}
