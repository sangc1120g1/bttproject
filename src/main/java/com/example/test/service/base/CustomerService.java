package com.example.test.service.base;

import com.example.test.model.Customer;
import com.example.test.service.CommonService;

import java.util.List;

public interface CustomerService extends CommonService<Customer> {
    Iterable<Customer> findCustomerLike(String username);
}
