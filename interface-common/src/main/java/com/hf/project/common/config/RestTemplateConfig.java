package com.hf.project.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @Description RestTemplate 配置
 * @Author fyq
 * @Date 2021/8/24 9:28
 **/

@Configuration(proxyBeanMethods = false)
public class RestTemplateConfig {

    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();


        //常用超时设置
        requestFactory.setReadTimeout(180000);
        requestFactory.setConnectTimeout(180000);
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public ClientHttpRequestFactory requestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(15000);
        return factory;
    }


}
