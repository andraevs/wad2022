package com.wad.springdata.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "student")
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private String iban;

    @ManyToOne
    private Student student;

    public Card(String iban) {
        this.iban = iban;
    }
}
