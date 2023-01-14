package com.mylearning.education_class_database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);
//    JPQL
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals( String firstName, Integer age);

    //    Native SQL
    @Query(value = "SELECT * FROM student s WHERE s.first_name = :firstName AND s.age >= :age", nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsNative(@Param("firstName") String firstName, @Param("age") Integer age);
}
