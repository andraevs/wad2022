package com.example.data.repositories;

import com.example.data.domain.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty,Long> {
    List<Faculty> findFacultiesByName(String name);
}
