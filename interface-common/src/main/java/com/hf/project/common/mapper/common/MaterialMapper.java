package com.hf.project.common.mapper.common;

import com.hf.project.common.entity.upload.Material;

import java.util.Date;
import java.util.List;

public interface MaterialMapper {

    /**
     * @Author fyq
     * @Description 根据物料编号返回物料信息
     * @Date 8:56 2021/8/30
     * @Param [billCodes]
     * @return java.util.List<com.hf.project.common.entity.upload.Material>
     **/
    List<Material> query(List<String> billCodes);

    /**
     * @Author fyq
     * @Description materialMapper
     * @Date 10:48 2021/9/3
     * @Param [billCode]
     * @return com.hf.project.common.entity.upload.Material
     **/
    Material querySingle(String billCode);

    /**
     * @Author fyq
     * @Description 查询全部物料单号
     * @Date 14:26 2021/10/16
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    List<String> queryAllMaterialNo();

    /**
     * @Author fyq
     * @Description 通过更新时间 返回大于更新时间数据
     * @Date 9:02 2021/10/18
     * @Param [syncDate]
     * @return java.util.List<java.lang.String>
     **/
    List<String> querySync(Date syncDate);
}
