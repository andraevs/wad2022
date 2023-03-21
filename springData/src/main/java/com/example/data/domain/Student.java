package com.example.data.domain;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"cards","faculties"})
@ToString(exclude = "faculties")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private Set<Faculty> faculties=new HashSet<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Card> cards=new HashSet<>();


    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void addCard(Card card){
        this.getCards().add(card);
        card.setStudent(this);
    }
}