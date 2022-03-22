package com.wad.springdata.bootstrap;

import com.wad.springdata.domain.Address;
import com.wad.springdata.domain.Card;
import com.wad.springdata.domain.Faculty;
import com.wad.springdata.domain.Student;
import com.wad.springdata.services.FacultyService;
import com.wad.springdata.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataLoader implements CommandLineRunner {
    private final StudentService studentService;
    private final FacultyService facultyService;

    public DataLoader(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @Override
    public void run(String... args) throws Exception {
        Address a1 = new Address("Bucharest");
        Student s1= new Student("John",a1);
        Card c1=new Card("123");
        Card c2=new Card("234");
        s1.addCard(c1);
        s1.addCard(c2);

        Faculty fils = new Faculty("FILS");
        fils.addStudent(s1);
        facultyService.save(fils);

        List<Faculty> faculties = facultyService.findAll();
        faculties.forEach(System.out::println);



    }
}
