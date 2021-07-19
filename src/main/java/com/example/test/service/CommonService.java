package com.example.test.service;

import com.example.test.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {
   T save(T t);
   void remove(Long id);
   Optional<T> findById(Long id);
   Iterable<T> findAll();

    List<T> findByCreateDate(String date);
}
