package com.example.test.service.impl;

import com.example.test.model.Student;
import com.example.test.repository.StudentRepository;
import com.example.test.service.base.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByCreateDate(String date) {
        date = "%"+date+"%";
        return studentRepository.findStudentByCreatedDate(date);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findStudentByNameContaining(name);
    }

    @Override
    public List<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable).getContent();
    }
}
