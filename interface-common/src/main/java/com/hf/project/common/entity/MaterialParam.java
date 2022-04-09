package com.hf.project.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 物料启动参数
 * @Author fyq
 * @Date 2021/10/16 15:48
 **/

@Data
public class MaterialParam implements Serializable {

    /** 物料是否上传 */
    private Boolean upload;

    /** 物料已经上传 */
    private List<String> uploadedMaterialNos = new ArrayList<>();

    /** 上传时间 */
    private Date uploadDate;

    /** 同步时间 */
    private Date syncDate;
}
