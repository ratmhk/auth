package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching
@SpringBootApplication

@MapperScan(basePackages = {"com.mapper"})
@EnableTransactionManagement
public class PerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerApplication.class, args);
    }

}
