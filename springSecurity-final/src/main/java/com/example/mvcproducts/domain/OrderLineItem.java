package com.example.mvcproducts.domain;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class OrderLineItem {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  private Product product;
  private int qty;


}
