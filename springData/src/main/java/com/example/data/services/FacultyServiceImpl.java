package com.example.data.services;

import com.example.data.domain.Faculty;
import com.example.data.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty save(Faculty f) {
        return facultyRepository.save(f);
    }

    @Override
    public List<Faculty> findAll() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findFacultiesByName(String name) {
        return facultyRepository.findFacultiesByName(name);
    }}
