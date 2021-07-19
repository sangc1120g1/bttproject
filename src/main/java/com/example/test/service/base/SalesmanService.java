package com.example.test.service.base;

import com.example.test.model.Salesman;
import com.example.test.service.CommonService;

import java.util.List;

public interface SalesmanService extends CommonService<Salesman> {
    List<Salesman> findByName(String name);
}
