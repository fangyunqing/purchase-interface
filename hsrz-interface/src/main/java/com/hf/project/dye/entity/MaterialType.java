package com.hf.project.dye.entity;

import lombok.Data;

/**
 * @Description 物料类型
 * @Author fyq
 * @Date 22/04/08 9:28
 **/

@Data
public class MaterialType {

    /** 主键 */
    private String guid;

    /** 父物料分类租主键 */
    private String materialTypeGUID;

    /** 物料分类姓名 */
    private String materialTypeName;

    /** 物料分类编码 */
    private String materialTypeCode;

}
