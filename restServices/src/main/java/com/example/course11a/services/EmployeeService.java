package com.example.course11a.services;

import com.example.course11a.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
  List<EmployeeDTO> getEmployees();
  EmployeeDTO getEmployeeById(Long id);
  EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
  void updateEmployee(Long id, EmployeeDTO employeeDTO);
  void deleteById(Long id);

}
