package com.hf.project.cloth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 成衣启动类
 * @Author fyq
 * @Date 2021/9/4 17:00
 **/

@SpringBootApplication
@ComponentScan({"com.hf.project.common","com.hf.project.cloth"})
@MapperScan({"com.hf.project.cloth.mapper", "com.hf.project.common.mapper"})
public class ClothApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothApplication.class, args);
    }

}
