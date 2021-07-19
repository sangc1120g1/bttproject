package com.example.test.repository;

import com.example.test.model.Teacher;
import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNameContaining(String name);

    @Query(value = "select * from teacher where username = ?1",nativeQuery = true)
    List<Teacher> findTeacherByCreatedDate(String date);
}
