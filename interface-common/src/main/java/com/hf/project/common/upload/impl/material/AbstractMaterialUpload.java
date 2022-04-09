package com.hf.project.common.upload.impl.material;

import com.alibaba.fastjson.JSONObject;
import com.hf.project.common.entity.enums.BillCodeType;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.service.MaterialService;
import com.hf.project.common.upload.AbstractUpLoad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 物料审核抽象类
 * @Author fyq
 * @Date 2021/8/30 11:50
 **/

public abstract class AbstractMaterialUpload extends AbstractUpLoad<Material> {

    private static final String SEPARATOR = "@@";

    @Autowired
    protected MaterialService materialService;

    @Override
    protected List<String> HandlerCodes(List<String> codes) {

        List<String> codeList = super.HandlerCodes(codes);

        if (codes.size() > 0) {
            if (codes.get(0).contains(SEPARATOR)) {
                return parse(codes).stream()
                        .map(Material::getCode)
                        .collect(Collectors.toList());
            }
            else {
                return codeList;
            }
        }
        else {
            return codeList;
        }
    }

    @Override
    protected List<Material> queryDataBase(List<String> codes) {

        List<Material> materials = null;
        if (codes.get(0).contains(SEPARATOR)) {
            materials = parse(codes);
        }
        else {
            materials = materialService.query(codes);
        }

        return materials;
    }

    @Override
    protected String getUploadName() {
        return BillCodeType.MATERIAL.getDescription();
    }

    @Override
    protected List<String> getBillCodes(List<Material> list) {
        return list.stream().map(Material::getCode).distinct().collect(Collectors.toList());
    }

    private List<Material> parse(List<String> codes) {

        List<Material> materials = new ArrayList<>();
        for (String code : codes) {

            String[] splitCode = code.split(SEPARATOR);
            JSONObject jsonObject = new JSONObject();
            for (String s : splitCode) {
                String[] keyValue = s.split("=");
                if (keyValue.length >= 2) {
                    jsonObject.put(keyValue[0], keyValue[1]);
                }
            }

            materials.add(jsonObject.toJavaObject(Material.class));

        }

        return materials;

    }

}
