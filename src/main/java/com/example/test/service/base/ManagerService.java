package com.example.test.service.base;

import com.example.test.model.Manager;
import com.example.test.service.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService extends CommonService<Manager> {
    List<Manager> findByName(String name);
    List<Manager> findAll(Pageable pageable);
}
