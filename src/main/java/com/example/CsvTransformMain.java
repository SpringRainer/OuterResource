package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2022/9/3 18:42
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class CsvTransformMain {
    public static void main(String[] args) {
        SpringApplication.run(CsvTransformMain.class, args);
    }
}
