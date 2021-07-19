package com.example.test.service.base;

import com.example.test.model.Lesson;
import com.example.test.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface LessonService extends CommonService<Lesson> {
    Optional<Lesson> findByDay(String day);

    Optional<Lesson> findByDate(String date);

    List<Lesson> findByCreateDate(String date);
}
