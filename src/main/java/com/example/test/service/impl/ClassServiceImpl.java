package com.example.test.service.impl;

import com.example.test.model.Class;
import com.example.test.repository.ClassRepository;
import com.example.test.service.base.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class save(Class aClass) {
        return classRepository.save(aClass);
    }

    @Override
    public void remove(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public Optional<Class> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Iterable<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public List<Class> findByCreateDate(String date) {
        return null;
    }

    @Override
    public List<Class> findClassByName(String className) {
        return classRepository.findByTypeOfClassContaining(className);
    }
}
