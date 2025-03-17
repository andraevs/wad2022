package com.example.data.services;

import com.example.data.domain.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty save(Faculty stud);
    List<Faculty> findAll();
    List<Faculty> findFacultiesByName(String name);
}