package com.example.test.service.impl;

import com.example.test.model.Teacher;
import com.example.test.repository.TeacherRepository;
import com.example.test.service.base.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void remove(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findByCreateDate(String date) {
        date = "%" + date + "%";
        return teacherRepository.findTeacherByCreatedDate(date);
    }

    @Override
    public List<Teacher> findByName(String name) {
        return teacherRepository.findByNameContaining(name);
    }
}
