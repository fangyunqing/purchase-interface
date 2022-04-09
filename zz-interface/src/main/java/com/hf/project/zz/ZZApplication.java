package com.hf.project.zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description 织造
 * @Author fyq
 * @Date 2021/10/6 8:43
 **/

@SpringBootApplication
@ComponentScan({"com.hf.project.common","com.hf.project.zz"})
public class ZZApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZZApplication.class, args);
    }

}
