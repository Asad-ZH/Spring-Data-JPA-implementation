package com.mylearning.education_class_database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentIdCardRepository
        extends JpaRepository<StudentIdCard, Long> {

}
