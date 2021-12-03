package com.example.myapp;


import com.example.myapp.configs.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MyappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

}
