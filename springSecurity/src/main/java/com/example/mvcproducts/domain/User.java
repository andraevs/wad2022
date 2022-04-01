package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class User{

  @Id
  @GeneratedValue
  private Long id;

  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

}