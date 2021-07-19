package com.example.test.service.impl;

import com.example.test.model.AutoCode;
import com.example.test.model.Customer;
import com.example.test.repository.AutoCodeRepository;
import com.example.test.service.base.AutoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoCodeServiceImpl implements AutoCodeService {

    @Autowired
    private AutoCodeRepository autoCodeRepository;

    @Override
    public AutoCode save(AutoCode autoCode) {
        return autoCodeRepository.save(autoCode);
    }

    @Override
    public void remove(Long id) {
        autoCodeRepository.deleteById(id);
    }

    @Override
    public Optional<AutoCode> findById(Long id) {
        return autoCodeRepository.findById(id);
    }

    @Override
    public Iterable<AutoCode> findAll() {
        return autoCodeRepository.findAll();
    }

    @Override
    public List<AutoCode> findByCreateDate(String date) {
        return null;
    }
}
