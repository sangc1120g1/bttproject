package com.example.test.service.base;

import com.example.test.model.Teacher;
import com.example.test.service.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService extends CommonService<Teacher> {
    List<Teacher> findByName(String name);
    List<Teacher> findAll(Pageable pageable);
}
