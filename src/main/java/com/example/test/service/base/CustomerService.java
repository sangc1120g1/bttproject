package com.example.test.service.base;

import com.example.test.model.Customer;
import com.example.test.service.CommonService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService extends CommonService<Customer> {
    Iterable<Customer> findCustomerLike(String username);
    List<Customer> findAll(Pageable pageable);
}
