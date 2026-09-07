package com.workforce.ems.Mapper;

import com.workforce.ems.dto.EmployeeDto;
import com.workforce.ems.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee emp){

        return new EmployeeDto(
                emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail()
        );}

    public static Employee mapToEmployee(EmployeeDto empdto) {

        return new Employee(
                empdto.getId(), empdto.getFirstName(), empdto.getLastName(), empdto.getEmail()
        );
    }

    }
