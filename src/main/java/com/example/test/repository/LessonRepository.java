package com.example.test.repository;

import com.example.test.model.Customer;
import com.example.test.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findLessonByDate(String date);

    Optional<Lesson> findLessonByDay(String day);

    @Query(value = "select * from lesson where created_date like ?1", nativeQuery = true)
    List<Lesson> findLessonByCreatedDate(String date);
}
