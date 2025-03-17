package com.example.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
