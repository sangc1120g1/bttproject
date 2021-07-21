package com.example.test.service.base;

import com.example.test.model.Salesman;
import com.example.test.service.CommonService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesmanService extends CommonService<Salesman> {
    List<Salesman> findByName(String name);
    List<Salesman> findAll(Pageable pageable);
}
