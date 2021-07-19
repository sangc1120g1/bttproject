package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.base.AutoCodeService;
import com.example.test.service.base.StudentService;
import com.example.test.service.base.UserService;
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
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @Autowired
    private AutoCodeService autoCodeService;

    @GetMapping("get")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        AutoCode autoCode = autoCodeService.findById(1L).get();
        long codeNumber = autoCode.getCodeNumber();
        codeNumber++;
        autoCode.setCodeNumber(codeNumber);
        student.setCode(EntityCode.autoCode("S", codeNumber));
        User user = userService.CreateUser(student.getCode(), "Student", "ROLE_CUSTOMER");
        student.setUser(user);
        student.setCreatedDate(LocalDateTime.now());
        student.setUpdateDate(LocalDateTime.now());
        autoCodeService.save(autoCode);
        return ResponseEntity.ok(studentService.save(student));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentService.findById(student.getId());
        studentOptional.ifPresent(value -> student.setCode(value.getCode()));
        return ResponseEntity.ok(studentService.save(student));
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> searchStudentByName(@PathVariable String name) {
        List<Student> students = studentService.findByName(name);
        if (students.size()>0) {
            return ResponseEntity.ok(students);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchCreateDate/{date}")
    public ResponseEntity<?> searchStudentByCreateDate(@PathVariable String date) {
        List<Student> students = studentService.findByCreateDate(date);
        if (students.size()>0) {
            return ResponseEntity.ok(students);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            studentService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
