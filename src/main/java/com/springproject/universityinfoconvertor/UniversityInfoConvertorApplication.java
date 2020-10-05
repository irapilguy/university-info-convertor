package com.springproject.universityinfoconvertor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class UniversityInfoConvertorApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityInfoConvertorApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {

            InfoProcessor infoProcessor = applicationContext.getBean("infoProcessor", InfoProcessor.class);
            infoProcessor.getStudentInfoList().forEach(studentInfo -> System.out.println(studentInfo.getName() + " " + studentInfo.getEmail()));
        };
    }
}
