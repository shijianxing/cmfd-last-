package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.DAO")
public class CmfdShiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfdShiApplication.class, args);
    }

}
