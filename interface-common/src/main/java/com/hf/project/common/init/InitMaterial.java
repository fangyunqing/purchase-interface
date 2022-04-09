package com.hf.project.common.init;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.component.FileMaterial;
import com.hf.project.common.entity.MaterialParam;
import com.hf.project.common.exception.InterFaceException;
import com.hf.project.common.service.MaterialService;
import com.hf.project.common.upload.impl.material.MaterialSaveUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 物料初次上传
 * @Author fyq
 * @Date 2021/10/16 14:33
 **/

@Component
@ConditionalOnProperty(prefix = "purchasing-cloud.request-property",value = "init_material", havingValue = "true",matchIfMissing = true)
public class InitMaterial implements ApplicationRunner {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialSaveUpload materialSaveUpload;

    @Autowired
    private FileMaterial fileMaterial;

    private static final Integer MAX_SIZE = 1;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        MaterialParam materialParam = fileMaterial.read();
        if (ObjectUtil.isNull(materialParam)) {
            materialParam = new MaterialParam();
            materialParam.setUpload(false);
        }
        else if (materialParam.getUpload()) {
            return;
        }

        List<String> materialNos = materialService.queryAllMaterialNo();
        if (CollectionUtil.isNotEmpty(materialParam.getUploadedMaterialNos())) {
            materialNos.removeAll(materialParam.getUploadedMaterialNos());
        }

        List<List<String>> splitMaterialNos = CollectionUtil.splitList(materialNos, MAX_SIZE);

        for (List<String> l : splitMaterialNos) {

            String res = materialSaveUpload.upLoad(StrUtil.join(",", l), null);
            if (res.equals("1")) {
                materialParam.getUploadedMaterialNos().addAll(l);
            }
            else {
                fileMaterial.write(materialParam);
                throw new InterFaceException(res);
            }
        }

        materialParam.setUpload(true);
        materialParam.getUploadedMaterialNos().clear();
        materialParam.setUploadDate(DateUtil.date());
        fileMaterial.write(materialParam);
    }
}
