package com.springproject.universityinfoconvertor;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String nameUKR;
    private String nameEN;
    private String email;
    private String password;

    public StudentInfo(String nameUKR, String nameEN, String email, String password) {
        this.nameUKR = nameUKR;
        this.nameEN = nameEN;
        this.email = email;
        this.password = password;
    }

    public StudentInfo() {
    }

    public String getNameUKR() {
        return nameUKR;
    }

    public String getNameEN() {
        return nameEN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() { return id; }
}
