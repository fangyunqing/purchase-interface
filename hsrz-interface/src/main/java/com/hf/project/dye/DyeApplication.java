package com.hf.project.dye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 染整接口启动类
 * @Author fyq
 * @Date 2021/8/24 11:31
 **/

@SpringBootApplication
@MapperScan({"com.hf.project.dye.mapper", "com.hf.project.common.mapper"})
@ComponentScan({"com.hf.project.common","com.hf.project.dye"})
public class DyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyeApplication.class, args);
    }
}
