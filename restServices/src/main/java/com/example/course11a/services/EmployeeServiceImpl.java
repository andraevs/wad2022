package com.example.course11a.services;

import com.example.course11a.model.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private Map<Long,EmployeeDTO> emps = new TreeMap<>();

  public EmployeeServiceImpl() {
    emps.put(1L, new EmployeeDTO(1L, "John", 12, true, "M"));
    emps.put(2L, new EmployeeDTO(2L, "Mary", 20, true, "F"));
    emps.put(3L, new EmployeeDTO(3L, "George", 30, false, "M"));
  }

  @Override
  public List<EmployeeDTO> getEmployees() {
    return new ArrayList<>(emps.values());
  }

  @Override
  public EmployeeDTO getEmployeeById(Long id) {
    return emps.get(id);
  }

  @Override
  public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
    Long id = Collections.max(emps.keySet()) + 1;
    employeeDTO.setId(id);
    emps.put(id,employeeDTO );
    return employeeDTO;
  }

  @Override
  public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
    emps.put(id, employeeDTO);
  }

  @Override
  public void deleteById(Long id) {
    emps.remove(id);
  }
}
