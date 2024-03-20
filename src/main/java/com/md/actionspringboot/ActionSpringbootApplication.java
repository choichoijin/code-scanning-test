package com.md.actionspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ActionSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionSpringbootApplication.class, args);
    }

}
