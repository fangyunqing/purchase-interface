package com.hf.project.common.entity;

import lombok.Data;

/**
 * @Description 用户信息
 * @Author fyq
 * @Date 2021/8/24 17:21
 **/

@Data
public class User {

    /** 主键 */
    private String id;

    /** 姓名 */
    private String userName;

    /** 工号 */
    private String userCode;

    /** 邮箱地址 */
    private String emailAddress;
}
