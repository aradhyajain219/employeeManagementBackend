package com.workforce.ems.service.Employeeimpl;

import com.workforce.ems.Mapper.EmployeeMapper;
import com.workforce.ems.dto.EmployeeDto;
import com.workforce.ems.entity.Employee;
import com.workforce.ems.repositry.EmployeeRepository;
import com.workforce.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import  com.workforce.ems.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override

    public EmployeeDto getEmployeeById(Long Id) {
        Employee employeeById = employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + Id));


        return EmployeeMapper.mapToEmployeeDto(employeeById);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee e : employees) {
            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(e));


        }
        return employeeDtos;


    }

    @Override
    public EmployeeDto updateEmployeeById(Long Id, EmployeeDto updatedEmployee) {
        Employee employeeById = employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + Id));
        employeeById.setFirstName(updatedEmployee.getFirstName());
        employeeById.setLastName(updatedEmployee.getLastName());
        employeeById.setEmail(updatedEmployee.getEmail());

        employeeRepository.save(employeeById);

        return EmployeeMapper.mapToEmployeeDto(employeeById);
    }

    @Override
    public Boolean deleteEmployeeById(Long Id) {
        Employee toBeDeletedemployee=employeeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + Id));

              employeeRepository.delete(toBeDeletedemployee);
              return  true;
    }



}
