package com.example.test.service.base;

import com.example.test.model.Class;
import com.example.test.service.CommonService;

import java.util.List;

public interface ClassService extends CommonService<Class> {
    List<Class> findClassByName(String className);
}
