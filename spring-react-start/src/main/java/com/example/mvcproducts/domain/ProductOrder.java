package com.example.mvcproducts.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductOrder {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<OrderLineItem> orderLineItems = new HashSet<>();

}
