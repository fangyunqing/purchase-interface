package com.hf.project.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 基础资料
 * @Author fyq
 * @Date 2021/9/8 9:53
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData {

    /** ID */
    private String id;

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** NC */
    private String NCCode;

    /** 备注 */
    private String memo;
}
