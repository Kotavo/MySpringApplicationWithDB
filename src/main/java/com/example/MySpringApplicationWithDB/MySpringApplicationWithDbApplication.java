package com.example.MySpringApplicationWithDB;

import com.example.MySpringApplicationWithDB.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.security.Security;


@SpringBootApplication
@EnableTransactionManagement
public class MySpringApplicationWithDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringApplicationWithDbApplication.class, args);
    }

}
