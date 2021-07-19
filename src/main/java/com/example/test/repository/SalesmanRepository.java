package com.example.test.repository;

import com.example.test.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
    List<Salesman> findByNameContaining(String name);

    @Query(value = "select * from salesman where created_date like ?1", nativeQuery = true)
    List<Salesman> findSalesmanByCreatedDate(String date);
}
