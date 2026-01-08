package com.employee.controller;


import com.employee.model.dto.EmployeeDto;
import com.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService ;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
         EmployeeDto response=   employeeService.saveEmployee(employeeDto);
          return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee( @PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updateEmp =  employeeService.updateEmployee(id,employeeDto);
         return new ResponseEntity<>(updateEmp,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return   new  ResponseEntity<>( "Employee Deleted SuccessFully", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto>  getSingleEmployee(@PathVariable Long id){
       EmployeeDto getSingleEmp =   employeeService.getSingleEmployee(id);
       return  new ResponseEntity<>(getSingleEmp,HttpStatus.OK);
    }

    @GetMapping("/allEmployess")
    public ResponseEntity<Iterable<EmployeeDto>> getAllEmployees(){
      Iterable<EmployeeDto> getAll=   employeeService.getAllEmployees();
       return   new ResponseEntity<>(getAll, HttpStatus.OK);
    }

}
