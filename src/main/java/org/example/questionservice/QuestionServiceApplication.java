package org.example.questionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class QuestionServiceApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(.class, args);

        SpringApplication.run(QuestionServiceApplication.class, args);
    }

}
