package com.example.test.controller;

import com.example.test.model.Lesson;
import com.example.test.service.base.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("lesson")
public class LessonController {


    @Autowired
    private LessonService lessonService;

    @GetMapping("get")
    public ResponseEntity<?> findAll() {
        Iterable<Lesson> customers = lessonService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        lesson.setCreatedDate(LocalDateTime.now());
        lesson.setUpdateDate(LocalDateTime.now());
        return ResponseEntity.ok(lessonService.save(lesson));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Lesson> editLesson(@RequestBody Lesson lesson) {
        Optional<Lesson> lessonOptional = lessonService.findById(lesson.getId());
        return lessonOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchLesson(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonService.findById(id);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{day}")
    public ResponseEntity<?> searchLessonByDay(@PathVariable String day) {
        Optional<Lesson> lesson = lessonService.findByDay(day);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{date}")
    public ResponseEntity<?> searchLessonByDate(@PathVariable String date) {
        Optional<Lesson> lesson = lessonService.findByDate(date);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search/create/{date}")
    public ResponseEntity<?> searchLessonByCreateDate(@PathVariable String date) {
        List<Lesson> lesson = lessonService.findByCreateDate(date);
        if (lesson.size()>0) {
            return ResponseEntity.ok(lesson);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonService.findById(id);
        if (lesson.isPresent()) {
            lessonService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
