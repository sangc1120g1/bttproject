package com.example.test.service.base;

import com.example.test.model.Teacher;
import com.example.test.service.CommonService;

import java.util.List;

public interface TeacherService extends CommonService<Teacher> {
    List<Teacher> findByName(String name);
}
