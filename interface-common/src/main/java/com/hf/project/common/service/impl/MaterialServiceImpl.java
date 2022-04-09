package com.hf.project.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.DiscordException;
import com.hf.project.common.mapper.common.MaterialMapper;
import com.hf.project.common.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 物料服务
 * @Author fyq
 * @Date 2021/8/25 17:29
 **/

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private RequiredProperties requiredProperties;

    @Override
    public List<Material> query(List<String> codes) {
        return materialMapper.query(codes);
    }

    @Override
    public List<String> queryAllMaterialNo() {
        return materialMapper.queryAllMaterialNo();
    }

    @Override
    public List<String> querySync(Date syncDate) {
        return materialMapper.querySync(syncDate);
    }

    @Override
    public Material queryByMaterialNo(String materialNo, Boolean ifNullThrowException) throws DiscordException {

        Material material = materialMapper.querySingle(materialNo);

        if (ObjectUtil.isNotNull(material)) {
            return material;
        }
        else {
            if (ifNullThrowException) {
                throw new DiscordException(requiredProperties.getSysName(),
                        DiscordException.DISCORD_MATERIAL,
                        materialNo);
            }
            else {
                return null;
            }
        }
    }


}
