package com.hf.project.common.entity;

import lombok.Data;

/**
 * @Description 邮件接收者
 * @Author fyq
 * @Date 2021/8/24 10:07
 **/

@Data
public class MailRecipient {

    /** 主送 */
    private String[] mailTo;

    /** 抄送 */
    private String[] mailCc;

    /** 暗抄送 */
    private String[] mailBcc;

    /** 邮件主题 */
    private String subject;

}
