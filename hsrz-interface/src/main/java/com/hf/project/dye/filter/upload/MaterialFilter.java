package com.hf.project.dye.filter.upload;

import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.filter.upload.UploadFilter;
import com.hf.project.common.mapper.common.MaterialMapper;
import com.hf.project.common.util.CommonUtil;
import com.hf.project.dye.entity.MaterialType;
import com.hf.project.dye.mapper.DyeUtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 物料拦截器
 * @Author fyq
 * @Date 22/04/08 9:16
 **/

@Component
public class MaterialFilter implements UploadFilter {

    @Autowired
    private DyeUtilMapper dyeUtilMapper;

    @Autowired
    private MaterialMapper materialMapper;

    private String errorMessage;

    private static final List<String> includeMaterialType = new ArrayList<>();

    private static final String PURCHASE = "采购";

    static {
        includeMaterialType.add("350AEEB7-E2E0-446B-A62F-864BC0BE800A");
        includeMaterialType.add("409BC4ED-69D9-4CC5-B2AF-124312AE3E17");
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public Boolean filter(Object o) {

        List<String> list = new ArrayList<>();
        CommonUtil.getValue(o, list, "code", "materialCode");

        if (list.size() == 0) {
            return true;
        }

        List<Material> excludeMaterialList = new ArrayList<>();
        list.forEach(s -> {
            Material material = materialMapper.querySingle(s);
            if (!PURCHASE.equals(material.getDefine1())) {
                excludeMaterialList.add(material);
            }
            else if (StrUtil.isNotBlank(material.getMarBasClassId())) {
                List<MaterialType> materialTypeList = dyeUtilMapper.queryMaterialTypeFatherTree(material.getMarBasClassId());
                if (materialTypeList.stream()
                        .noneMatch(materialType -> includeMaterialType.contains(materialType.getGuid()))) {
                    excludeMaterialList.add(material);
                }

            }
        });

        if (excludeMaterialList.size() == 0) {
            return true;
        }
        else if (list.size() == excludeMaterialList.size()) {
            errorMessage = "1";
            return false;
        }
        else {
            errorMessage = StrUtil.format("{}的[物料分类]或者[类型非采购]不能上传",
                    StrUtil.join(",", excludeMaterialList.stream().map(Material::getCode).collect(Collectors.toList())));
            return false;
        }
    }
}
