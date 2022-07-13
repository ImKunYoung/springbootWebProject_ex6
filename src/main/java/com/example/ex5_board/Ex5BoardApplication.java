package com.example.ex5_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing /*AuditingEntityListener 활성화를 위함*/
public class Ex5BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ex5BoardApplication.class, args);
    }

}
