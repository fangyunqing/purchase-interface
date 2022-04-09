package com.hf.project.common.upload.impl.material;

import com.hf.project.common.entity.enums.ActionName;
import com.hf.project.common.util.CommonObjectUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 物料上传
 * @Author fyq
 * @Date 2021/8/26 8:17
 **/

@Component
public class MaterialSaveUpload extends AbstractMaterialUpload {


    @Override
    protected String getUpLoadUrl() {
        return interfaceUrl.getSaveMaterialUrl();
    }

    @Override
    protected Object getObject(List dataList) {
        return CommonObjectUtil.getCommonInsertTenant(dataList, requiredProperties);
    }

    @Override
    protected ActionName getActionName() {
        return ActionName.INSERT;
    }
}
