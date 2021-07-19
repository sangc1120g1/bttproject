package com.example.test.service.impl;

import com.example.test.model.Lesson;
import com.example.test.repository.LessonRepository;
import com.example.test.service.base.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void remove(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public Iterable<Lesson> findAll() {
        return lessonRepository.findAll();
    }


    @Override
    public Optional<Lesson> findByDay(String day) {
        return lessonRepository.findLessonByDay(day);
    }

    @Override
    public Optional<Lesson> findByDate(String date) {
        return lessonRepository.findLessonByDate(date);
    }

    @Override
    public List<Lesson> findByCreateDate(String date) {
        date = "%"+date+"%";
        return lessonRepository.findLessonByCreatedDate(date);
    }

}
