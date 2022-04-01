package com.wad.springdata.repositories;

import com.wad.springdata.domain.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty,Long> {
    List<Faculty> findFacultiesByName(String name);
}
