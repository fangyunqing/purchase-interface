package com.hf.project.common.check.record;

import com.hf.project.common.entity.enums.RecordType;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.mapper.common.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 物料检查服务
 * @Author fyq
 * @Date 2021/11/23 8:47
 **/

@Service
public class CheckMaterialService extends AbstractCheckRecord<Material> {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    protected Material doCheck(String... args) {
        String materialNo = args[0];
        return materialMapper.querySingle(materialNo);
    }

    @Override
    protected RecordType getRecordType() {
        return RecordType.MATERIAL;
    }
}
