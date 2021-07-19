package com.example.test.repository;

import com.example.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByNameContaining(String name);


    @Query(value = "select * from student where created_date like ?1", nativeQuery = true)
    List<Student> findStudentByCreatedDate(String date);
}
