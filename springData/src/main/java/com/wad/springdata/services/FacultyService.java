package com.wad.springdata.services;

import com.wad.springdata.domain.Faculty;
import com.wad.springdata.domain.Student;

import java.util.List;

public interface FacultyService {
    Faculty save(Faculty stud);
    List<Faculty> findAll();
}
