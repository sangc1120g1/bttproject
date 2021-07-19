package com.example.test.service.base;

import com.example.test.model.Manager;
import com.example.test.service.CommonService;

import java.util.List;

public interface ManagerService extends CommonService<Manager> {
    List<Manager> findByName(String name);
}
