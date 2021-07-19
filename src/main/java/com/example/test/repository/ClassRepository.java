package com.example.test.repository;

import com.example.test.model.Class;
import com.example.test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    @Query(value = "select * from class where created_date like ?1", nativeQuery = true)
    List<Class> findByTypeOfClassContaining(String typeOfClass);
}
