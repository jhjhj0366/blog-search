package com.jhj.blogsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.jhj.blogsearch")
public class BlogSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApplication.class, args);
    }

}
