package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.base.AutoCodeService;
import com.example.test.service.base.TeacherService;
import com.example.test.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private AutoCodeService autoCodeService;

    @GetMapping("get")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("get/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page , @PathVariable("size") int size){
        PageRequest pageable = PageRequest.of(page , size);
        return ResponseEntity.ok( teacherService.findAll((pageable)));
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        AutoCode autoCode = autoCodeService.findById(1L).get();
        long codeNumber = autoCode.getCodeNumber();
        codeNumber++;
        autoCode.setCodeNumber(codeNumber);
        teacher.setCode(EntityCode.autoCode("T", codeNumber));
        User user = userService.CreateUser(teacher.getCode(), "Teacher", "ROLE_CUSTOMER");
        teacher.setUser(user);
        teacher.setCreatedDate(LocalDateTime.now());
        teacher.setUpdateDate(LocalDateTime.now());
        autoCodeService.save(autoCode);
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<Teacher> editTeacher(@RequestBody Teacher teacher) {
        Optional<Teacher> customerOptional = teacherService.findById(teacher.getId());
        customerOptional.ifPresent(value -> teacher.setCode(value.getCode()));
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchTeacher(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            return ResponseEntity.ok(teacher.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> searchTeacherByName(@PathVariable String name) {
        List<Teacher> teachers = teacherService.findByName(name);
        if (teachers.size()>0) {
            return ResponseEntity.ok(teachers);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/searchCreateDate/{date}")
    public ResponseEntity<?> searchTeacherByCreateDate(@PathVariable String date) {
        List<Teacher> teachers = teacherService.findByCreateDate(date);
        if (teachers.size()>0) {
            return ResponseEntity.ok(teachers);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (teacher.isPresent()) {
            teacherService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
