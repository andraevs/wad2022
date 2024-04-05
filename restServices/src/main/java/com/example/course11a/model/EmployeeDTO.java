package com.example.course11a.model;

import java.util.Objects;


public class EmployeeDTO {
  private Long id;
  private String name;
  private int age;
  private boolean active;
  private String gender;

  public EmployeeDTO() {
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeDTO that = (EmployeeDTO) o;
    return age == that.age && active == that.active && Objects.equals(name, that.name) && Objects.equals(gender, that.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, active, gender);
  }

  public EmployeeDTO(Long id, String name, int age, boolean active, String gender) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.active = active;
    this.gender = gender;
  }
}
