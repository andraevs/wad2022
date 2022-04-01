package com.wad.springdata.services;

import com.wad.springdata.domain.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty save(Faculty stud);
    List<Faculty> findAll();

    List<Faculty> findFacultiesByName(String name);
}
