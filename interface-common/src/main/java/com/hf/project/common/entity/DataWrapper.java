package com.hf.project.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description 数据包装类
 * @Author fyq
 * @Date 2021/8/30 11:36
 **/

@Data
public class DataWrapper {

    /** 真实数据 */
    private List dataList;

    /** 单据号 */
    private List<String> billCodes;

}
