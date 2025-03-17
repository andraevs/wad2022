package com.example.data.repositories;

import com.example.data.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findAll(); // overrides findAll to return a List
    List<Student> findByName(String name);
    List<Student> findByCardsEmpty();
}
