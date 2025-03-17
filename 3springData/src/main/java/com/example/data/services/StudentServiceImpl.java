package com.example.data.services;

import com.example.data.domain.Student;
import com.example.data.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student stud) {
        return studentRepository.save(stud);
    }
}
