package com.hwua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.hwua.mapper"})
public class CrmSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmSystemApplication.class, args);
    }

}
