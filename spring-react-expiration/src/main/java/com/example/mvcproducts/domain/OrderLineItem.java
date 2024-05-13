package com.example.mvcproducts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class OrderLineItem {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  private Product product;
  private int qty;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderLineItem that = (OrderLineItem) o;
    return qty == that.qty && Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, qty);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public OrderLineItem() {
  }


  public OrderLineItem(Long id, Product product, int qty) {
    this.id = id;
    this.product = product;
    this.qty = qty;
  }
}
