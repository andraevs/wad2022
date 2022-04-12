package com.example.mvcproducts.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
