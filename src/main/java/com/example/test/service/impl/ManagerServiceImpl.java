package com.example.test.service.impl;

import com.example.test.model.Manager;
import com.example.test.repository.ManagerRepository;
import com.example.test.service.base.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public void remove(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Iterable<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public List<Manager> findByCreateDate(String date) {
        date = "%" + date + "%";
        return managerRepository.findManagerByCreatedDate(date);
    }

    @Override
    public List<Manager> findByName(String name) {
        return managerRepository.findManagerByNameContaining(name);
    }

    @Override
    public List<Manager> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable).getContent();
    }
}
