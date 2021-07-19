package com.example.test.repository;

import com.example.test.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findManagerByNameContaining(String name);

    @Query(value = "select * from manager where created_date like ?1", nativeQuery = true)
    List<Manager> findManagerByCreatedDate(String date);
}
