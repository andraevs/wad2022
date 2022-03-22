package com.wad.springdata.services;

import com.wad.springdata.domain.Student;
import com.wad.springdata.repositories.StudentRepository;
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
