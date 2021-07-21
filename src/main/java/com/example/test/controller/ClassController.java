package com.example.test.controller;

import com.example.test.model.Class;
import com.example.test.service.base.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("get")
    public ResponseEntity<?> findAllClass() {
        Iterable<Class> classes = classService.findAll();
        return ResponseEntity.ok(classes);
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Class> createClass(@RequestBody Class classes) {
        classes.setCreatedDate(LocalDateTime.now());
        classes.setUpdateDate(LocalDateTime.now());
        return ResponseEntity.ok(classService.save(classes));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Class> editClass(@RequestBody Class classes) {
        Optional<Class> classOptional = classService.findById(classes.getId());
        return classOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchClass(@PathVariable Long id) {
        Optional<Class> classOptional = classService.findById(id);
        if (classOptional.isPresent()) {
            return ResponseEntity.ok(classOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchDate/{date}")
    public ResponseEntity<?> searchClassByDate(@PathVariable String date) {
        List<Class> classes = classService.findByCreateDate(date);
        if (classes.size() > 0) {
            return ResponseEntity.ok(classes);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchTypeOfClass/{className}")
    public ResponseEntity<?> searchClassByTypeOfClass(@PathVariable String className) {
        List<Class> classes = classService.findClassByName(className);
        if (classes.size() > 0) {
            return ResponseEntity.ok(classes);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<?> deleteClass(@PathVariable Long id) {
        Optional<Class> classOptional = classService.findById(id);
        if (classOptional.isPresent()) {
            classService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
