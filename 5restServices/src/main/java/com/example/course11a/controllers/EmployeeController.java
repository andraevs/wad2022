package com.example.course11a.controllers;

import com.example.course11a.model.EmployeeDTO;
import com.example.course11a.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // @Controller + @Response
@RequestMapping("/api/v1/employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(value= "", produces = "application/json" )
  public ResponseEntity<List<EmployeeDTO>> getFilteredEmployees(
          @RequestParam(required = false) String gender,
          @RequestParam(required = false) Boolean active){
    return new ResponseEntity<>(employeeService.getEmployees(gender,active), HttpStatus.OK);
  }

  @GetMapping("/{empId}")  // /api/v1/employees/2  OR /api/v1/employees?id=2
  public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("empId") Long empId){
    return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
  }

  //add and also return the location of the new resource
  @PostMapping(value = "", consumes = "application/json")
  public ResponseEntity<?> postEmployee(@RequestBody @Valid EmployeeDTO emp, BindingResult result){
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors());
    }
    EmployeeDTO savedEmp = employeeService.saveEmployee(emp);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location","/api/v1/employees/"+ savedEmp.getId().toString());
    return new ResponseEntity<>(headers,HttpStatus.CREATED);
  }

  @PutMapping("/{empId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEmployee(@PathVariable("empId") Long empId, @RequestBody EmployeeDTO emp){
    employeeService.updateEmployee(empId,emp);
  }

  @DeleteMapping("/{empId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployee(@PathVariable("empId") Long id){
    employeeService.deleteById(id);
  }

}
