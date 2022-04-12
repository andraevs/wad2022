package com.example.course11a.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
  private Long id;
  private String name;
  private int age;
  private boolean active;
  private String gender;
}
