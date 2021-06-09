package com.leslie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Leslie
 * @create 2021/5/19 14:24
 */
@SpringBootApplication()
public class MainApplication {
    public static void main(String args[]){
        SpringApplication.run(MainApplication.class, args);
    }
}
