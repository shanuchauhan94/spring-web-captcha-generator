package org.spring.captcha.service;

import org.spring.captcha.model.Student;
import org.spring.captcha.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public String createUser(Student user) {
        try {
            Student s = repository.save(user);
            return "Student with Id: " + s.getId();
        } catch (Exception e) {
            return "failed " + e.getMessage();
        }
    }

    @Override
    public List<Student> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> getOneUser(Integer id) {
        return repository.findById(id);
    }
}
