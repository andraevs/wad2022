package com.wad.springdata.services;

import com.wad.springdata.domain.Faculty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
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
