package com.hf.project.common.service;

import com.hf.project.common.entity.upload.Material;
import com.hf.project.common.exception.DiscordException;

import java.util.Date;
import java.util.List;

public interface MaterialService {

    List<Material> query(List<String> codes);

    List<String> queryAllMaterialNo();

    List<String> querySync(Date syncDate);

    Material queryByMaterialNo(String materialNo, Boolean IfNullThrowException) throws DiscordException;
}
