package org.tsdes.frontend;

import org.springframework.boot.SpringApplication;
import org.tsdes.Application;

public class LocalApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--spring.profiles.active=test");
    }
}