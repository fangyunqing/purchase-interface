package com.hf.project.common.helper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hf.project.common.entity.MailRecipient;
import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.function.MailRecipientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

/**
 * @Description 邮件发送
 * @Author fyq
 * @Date 2021/5/6 17:16
 **/

@Component
public class MailHelper {

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private RequiredProperties requiredProperties;

    /** 维护人员的邮件信息 */
    @Autowired
    @Qualifier("MaintenanceStaffMail")
    private MailRecipient maintenanceStaffMail;

    private static final String DEFAULT_SUBJECT = "主题";

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    private static final Logger log = LogManager.getRootLogger();

    /**
     * @Author fyq
     * @Description 采购云调用API异常处理
     * @Date 16:31 2021/8/24
     * @Param [errorMessage, mailRecipientHandler]
     * @return void
     **/
    @Async("MailExecutor")
    public void sendInvokeExceptionMail(@NotNull String errorMessage){

        try{

            MimeMessageHelper mimeMessageHelper = createMimeMessage(maintenanceStaffMail);

            if (ObjectUtil.isNotNull(mimeMessageHelper)){

                Context context = new Context();
                context.setVariable("subject", requiredProperties.getSysName());
                context.setVariable("errorMessage", errorMessage);
                context.setVariable("invokeTime", DateUtil.now());

                mimeMessageHelper.setText(templateEngine.process("InvokeException", context), true);
                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            }

        }catch (MessagingException e){
            log.error(e);
        }
    }

    /**
     * @Author fyq
     * @Description 本地异常处理
     * @Date 16:38 2021/8/24
     * @Param [request, errorMessage]
     * @return void
     **/
    @Async("MailExecutor")
    public void sendLocalExceptionMail(@NotNull String requestData, @NotNull String errorMessage){

        try {

            MimeMessageHelper mimeMessageHelper = createMimeMessage(maintenanceStaffMail);

            if (ObjectUtil.isNotNull(mimeMessageHelper)){

                Context context = new Context();
                context.setVariable("request", requestData);
                context.setVariable("subject", requiredProperties.getSysName());
                context.setVariable("errorMessage", errorMessage);
                context.setVariable("failTime", DateUtil.now());

                mimeMessageHelper.setText(templateEngine.process("LocalException", context), true);


                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            }

        } catch (MessagingException e) {
            log.error(e);
        }
    }

    /**
     * @Author fyq
     * @Description 发送上传时的json数据
     * @Date 8:59 2021/8/25
     * @Param [json, mailRecipientHandler]
     * @return void
     **/
    @Async("MailExecutor")
    public void sendJsonMain(@NotNull String json, MailRecipientHandler mailRecipientHandler){

        try{

            MimeMessageHelper mimeMessageHelper = createMimeMessage(mailRecipientHandler.getMailRecipient());

            if (ObjectUtil.isNotNull(mimeMessageHelper)){

                mimeMessageHelper.setText(json);

                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            }

        }catch (MessagingException e) {
            log.error(e);
        }
    }

    /**
     * @Author fyq
     * @Description 上传失败邮件处理
     * @Date 8:56 2021/8/25
     * @Param [title, oddNumbers, errorMessage, mailRecipientHandler]
     * @return void
     **/
    @Async("MailExecutor")
    public void sendFailUploadMessage(String title, List<String> codes, String errorMessage, MailRecipientHandler mailRecipientHandler){

        try{

            MimeMessageHelper mimeMessageHelper = createMimeMessage(mailRecipientHandler.getMailRecipient());

            if (ObjectUtil.isNotNull(mimeMessageHelper)){

                Context context = new Context();
                context.setVariable("title", title);
                context.setVariable("errorMessage", errorMessage);
                context.setVariable("messageNos", StrUtil.join(",", codes));
                context.setVariable("failTime", DateUtil.now());

                mimeMessageHelper.setText(templateEngine.process("UploadFail", context), true);

                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            }

        } catch (MessagingException e) {
            log.error(e);
        }

    }

    /**
     * @Author fyq
     * @Description 上传成功邮件处理
     * @Date 8:57 2021/8/25
     * @Param [title, oddNumbers, mailRecipientHandler]
     * @return void
     **/
    @Async("MailExecutor")
    public void sendSuccessUploadMessage(String title, List<String> codes, MailRecipientHandler mailRecipientHandler){

        try{

            MimeMessageHelper mimeMessageHelper = createMimeMessage(mailRecipientHandler.getMailRecipient());

            if (ObjectUtil.isNotNull(mimeMessageHelper)){

                Context context = new Context();
                context.setVariable("title", title);
                context.setVariable("messageNos", StrUtil.join(",", codes));
                context.setVariable("successTime", DateUtil.now());

                mimeMessageHelper.setText(templateEngine.process("UploadFail", context), true);

                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            }

        } catch (MessagingException e) {
            log.error(e);
        }

    }

    /**
     * @Author fyq
     * @Description 创建邮件信息
     * @Date 8:58 2021/8/25
     * @Param [mailRecipient]
     * @return org.springframework.mail.javamail.MimeMessageHelper
     **/
   private MimeMessageHelper createMimeMessage(MailRecipient mailRecipient) throws MessagingException {

       if (ObjectUtil.isNull(mailRecipient) || ArrayUtil.isEmpty(mailRecipient.getMailTo())){
           return null;
       }

       MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), false);

       // 邮件发送人
       mimeMessageHelper.setFrom(mailFrom);
       // 邮件主送
       mimeMessageHelper.setTo(mailRecipient.getMailTo());
       // 邮件抄送
       if (ArrayUtil.isNotEmpty(mailRecipient.getMailCc())){
           mimeMessageHelper.setCc(mailRecipient.getMailCc());
       }
       // 邮件暗抄送
       if (ArrayUtil.isNotEmpty(mailRecipient.getMailBcc())){
           mimeMessageHelper.setBcc(mailRecipient.getMailBcc());
       }

       // 邮件发送日期
       mimeMessageHelper.setSentDate(new Date());

       // 邮件主题
       if (StrUtil.isBlank(mailRecipient.getSubject())) {
           mimeMessageHelper.setSubject(DEFAULT_SUBJECT);
       } else {
           mimeMessageHelper.setSubject(mailRecipient.getSubject());
       }


       return mimeMessageHelper;

   }
}
