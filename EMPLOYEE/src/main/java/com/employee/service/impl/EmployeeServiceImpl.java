package com.employee.service.impl;

import com.employee.model.dto.EmployeeDto;
import com.employee.model.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private  final EmployeeRepository employeeRepository ;
    private final ModelMapper modelMapper ;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if(employeeDto.getId() != null){
            throw new RuntimeException("Employee already Exist");
        }
        Employee e =   modelMapper.map(employeeDto, Employee.class);

       Employee saveEntity=  employeeRepository.save(e);


        return  modelMapper.map(saveEntity ,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {

        if(id==null || employeeDto.getId()==null ){
            throw new RuntimeException("Please provide id");
        }

        if(!Objects.equals(id,employeeDto.getId())){
            throw  new RuntimeException("ID MisMatch");
        }
        employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));

            Employee newEmp =  modelMapper.map(employeeDto, Employee.class);
            Employee updatedEmployee = employeeRepository.save(newEmp);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));
    employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDto getSingleEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));
        return modelMapper.map(employee, EmployeeDto.class) ;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> getAllEmp = employeeRepository.findAll();
       return getAllEmp.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).toList();

    }
}


