package com.springproject.universityinfoconvertor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class EmailGenerator {

    private static final String domain = "@student.sumdu.edu.ua";

    public String getGeneratedEmail(String studentName) {
        List<String> name = new ArrayList<>();
        try {
            name = Arrays.stream(studentName.split(" ")).collect(Collectors.toList());
            String email = name.get(1).toLowerCase() + "." + name.get(0).toLowerCase();

            return email + domain;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public String getGeneratedPassword() {
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        symbols += symbols.toUpperCase() + numbers;
        String password = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return password;
    }
}
