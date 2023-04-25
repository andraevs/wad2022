package com.example.mvcproducts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

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
