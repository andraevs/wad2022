package com.example.data.services;

import com.example.data.domain.Faculty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceEm implements FacultyService {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Faculty save(Faculty f) {
        em.persist(f);
        return f; }
    @Override
    public List<Faculty> findAll() {
        TypedQuery<Faculty> query = em.createQuery("select f from Faculty f",Faculty.class);
        return query.getResultList();
    }

    @Override
    public List<Faculty> findFacultiesByName(String name) {
        TypedQuery<Faculty> query = em.createQuery("select f from Faculty f join fetch f.students " +
                "where f.name = :name",Faculty.class);
        query.setParameter("name",name);
        return query.getResultList();
    }}
