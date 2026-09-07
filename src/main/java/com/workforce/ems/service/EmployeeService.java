package com.workforce.ems.service;

import com.workforce.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long Id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployeeById(Long Id,EmployeeDto updatedEmployee);

    Boolean deleteEmployeeById(Long Id);




}
