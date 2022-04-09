package com.hf.project.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.hf.project.common.entity.MailRecipient;
import com.hf.project.common.entity.properties.InterfaceUrl;
import com.hf.project.common.entity.properties.RequiredProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 属性配置类
 * @Author fyq
 * @Date 2021/8/24 13:33
 **/

@Configuration(proxyBeanMethods = false)
public class PropertyConfig {

    @Bean(name = "RequiredProperties")
    @ConfigurationProperties("purchasing-cloud.request-property")
    public RequiredProperties getRequiredProperties() {
        return new RequiredProperties();
    }

    @Bean(name = "InterfaceUrl")
    @ConfigurationProperties("purchasing-cloud.interface-url")
    public InterfaceUrl getInterfaceUrl() {
        return new InterfaceUrl();
    }

    @Bean(name = "MaintenanceStaffMail")
    public MailRecipient getMaintenanceStaffMail(RequiredProperties requiredProperties) {

        MailRecipient mailRecipient = new MailRecipient();

        mailRecipient.setMailTo(requiredProperties.getMaintenanceStaffMail());

        String subject = "系统异常,请尽快处理";

        if (ObjectUtil.isNotNull(requiredProperties.getSysName())) {
            subject = requiredProperties.getSysName() + subject;
        }

        mailRecipient.setSubject(subject);

        return mailRecipient;
    }
}
