package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "students")
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "faculty_student",joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students=new HashSet<>();

    public Faculty(String name) {
        this.name = name;
    }

    public void addStudent(Student s){
        students.add(s);
        s.getFaculties().add(this);
    }
}
