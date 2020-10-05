package com.springproject.universityinfoconvertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.transaction.Transactional;

@Component
public class StudentInfoDB {

    private InfoProcessor infoProcessor;
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    public StudentInfoDB(InfoProcessor infoProcessor, StudentInfoRepository studentInfoRepository) {
        this.infoProcessor = infoProcessor;
        this.studentInfoRepository = studentInfoRepository;
    }

    @Bean
    public void fillStudentInfoTable() {
        infoProcessor.getStudentInfoList().forEach(studentInfo ->
            studentInfoRepository.insertStudentInfo(studentInfo));
    }
}
