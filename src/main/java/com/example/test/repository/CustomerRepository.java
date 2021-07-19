package com.example.test.repository;

import com.example.test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customer where created_date like ?1", nativeQuery = true)
    List<Customer> findCustomerByCreatedDate(String createdDate);

    Iterable<Customer> findByNameContaining(String username);
}
