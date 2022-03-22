package com.wad.springdata.repositories;

import com.wad.springdata.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
