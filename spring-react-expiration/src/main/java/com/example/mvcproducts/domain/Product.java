package com.example.mvcproducts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Product {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
//  @Enumerated(EnumType.ORDINAL)
  private ProductType type;
  private String description;
  private double price;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name) && type == product.type && Objects.equals(description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, description, price);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Product() {
  }

  public Product(String name, ProductType type, String description, double price) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
  }


}
