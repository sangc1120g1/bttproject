package com.example.test.service.base;

import com.example.test.model.Student;
import com.example.test.service.CommonService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService extends CommonService<Student> {
    List<Student> findByName(String name);
    List<Student> findAll(Pageable pageable);
}
