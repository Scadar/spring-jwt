package com.example.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);
    }

}
