package com.example.test.service.base;

import com.example.test.model.Student;
import com.example.test.service.CommonService;

import java.util.List;

public interface StudentService extends CommonService<Student> {
    List<Student> findByName(String name);
}
