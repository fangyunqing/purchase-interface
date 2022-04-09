package com.hf.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 邮件线程池配置
 * @Author fyq
 * @Date 2021/8/24 9:19
 **/

@Configuration(proxyBeanMethods = false)
@EnableAsync
public class MailThreadPoolConfig {

    @Bean(name = "MailExecutor")
    public Executor asyncServiceExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(8);
        //配置最大线程数
        executor.setMaxPoolSize(18);
        //配置队列大小
        executor.setQueueCapacity(28);
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }

}
