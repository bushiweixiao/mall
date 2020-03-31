package com.lsz.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.lsz.mall.user.mapper")
public class MallUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserServiceApplication.class, args);
    }

}
