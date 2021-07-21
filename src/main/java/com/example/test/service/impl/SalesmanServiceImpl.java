package com.example.test.service.impl;

import com.example.test.model.Salesman;
import com.example.test.repository.SalesmanRepository;
import com.example.test.service.base.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanServiceImpl implements SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Override
    public Salesman save(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    @Override
    public void remove(Long id) {
        salesmanRepository.deleteById(id);
    }

    @Override
    public Optional<Salesman> findById(Long id) {
        return salesmanRepository.findById(id);
    }

    @Override
    public Iterable<Salesman> findAll() {
        return salesmanRepository.findAll();
    }

    @Override
    public List<Salesman> findByCreateDate(String date) {
        date = "%"+date+"%";
        return salesmanRepository.findSalesmanByCreatedDate(date);
    }

    @Override
    public List<Salesman> findByName(String name) {
        return salesmanRepository.findByNameContaining(name);
    }

    @Override
    public List<Salesman> findAll(Pageable pageable) {
        return salesmanRepository.findAll(pageable).getContent();
    }
}
