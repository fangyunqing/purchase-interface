package com.hf.project.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Description 邮件
 * @Author fyq
 * @Date 2021/5/6 17:12
 **/

@Getter
@Setter
public class MailMessage {

    private String from;

    private String[] to;

    private String subject;

    private String text;

    private Date sentDate;

    private String[] cc;

    private String[] bcc;

    private String status;

    private String error;
    @JsonIgnore
    private MultipartFile[] multipartFiles;

}
