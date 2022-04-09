package com.hf.project.common.upload.impl.material;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 物料停用
 * @Author fyq
 * @Date 2021/8/26 9:34
 **/

@Component
public class MaterialDisableUpload extends AbstractMaterialUpload {

    @Override
    protected List filterData(List<Material> list) {
        return list.stream()
                .map(Material::getCode)
                .collect(Collectors.toList());
    }

    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getDisableMaterialUrl();
    }

    @Override
    protected Object getObject(List dataList) {
        return CommonObjectUtil.getCommonDisableTenant(dataList, requiredProperties);
    }

    @Override
    protected ActionName getActionName() {
        return ActionName.DISABLE;
    }

}
