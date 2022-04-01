package com.wad.springdata.repositories;

import com.wad.springdata.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    @Query("SELECT S from Student S")
    List<Student> findAll();

    @Query("SELECT S from Student S WHERE S.name = :name")
    List<Student> findByName(String name);

    @Query("SELECT S FROM Student S WHERE S.cards IS EMPTY")
    List<Student> findByCardsEmpty();

}
