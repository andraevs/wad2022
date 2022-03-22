package com.wad.springdata.repositories;

import com.wad.springdata.domain.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepository extends CrudRepository<Faculty,Long> {
}
