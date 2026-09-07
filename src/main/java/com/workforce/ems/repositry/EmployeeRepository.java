package com.workforce.ems.repositry;

import com.workforce.ems.dto.EmployeeDto;
import com.workforce.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {




}
