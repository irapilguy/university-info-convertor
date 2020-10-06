package com.springproject.universityinfoconvertor;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class StudentInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertStudentInfo(StudentInfo studentInfo) {
        entityManager.createNativeQuery("INSERT IGNORE INTO students (id, name, email) VALUES(?,?,?)")
                .setParameter(1, studentInfo.getId())
                .setParameter(2, studentInfo.getNameEN())
                .setParameter(3, studentInfo.getEmail())
                .executeUpdate();
    }
}
