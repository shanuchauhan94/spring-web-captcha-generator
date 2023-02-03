package org.spring.captcha.service;

import org.spring.captcha.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    String createUser(Student user);

    List<Student> getAllUsers();

    Optional<Student> getOneUser(Integer Id);
}
