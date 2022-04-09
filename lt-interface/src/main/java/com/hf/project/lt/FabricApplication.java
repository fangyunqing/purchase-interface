package com.hf.project.lt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 蓝天面料启动类
 * @Author fyq
 * @Date 2021/9/13 8:27
 **/

@SpringBootApplication
@MapperScan({"com.hf.project.lt.mapper", "com.hf.project.common.mapper"})
@ComponentScan({"com.hf.project.common","com.hf.project.lt"})
public class FabricApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabricApplication.class, args);
    }
}
