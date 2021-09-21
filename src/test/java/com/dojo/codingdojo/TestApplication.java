package com.dojo.codingdojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TestApplication.class, args);
        int exitCode = SpringApplication.exit(ctx);
        System.exit(exitCode);
    }

}
