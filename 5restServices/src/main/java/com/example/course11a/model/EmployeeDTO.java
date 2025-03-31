package com.example.course11a.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
  private Long id;
  @NotBlank
  private String name;
  @Min(18)
  private int age;
  private boolean active;
  @Pattern(regexp = "M|F")
  private String gender;
}
