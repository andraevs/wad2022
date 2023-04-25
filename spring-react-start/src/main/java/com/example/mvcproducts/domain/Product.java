package com.example.mvcproducts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
//  @Enumerated(EnumType.ORDINAL)
  private ProductType type;
  private String description;
  private double price;

  public Product(String name, ProductType type, String description, double price) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
  }


}
