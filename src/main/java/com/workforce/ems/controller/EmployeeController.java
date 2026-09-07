package com.workforce.ems.controller;


import com.workforce.ems.dto.EmployeeDto;
import com.workforce.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
 @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee =employeeService.createEmployee(employeeDto);

        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);



    }



    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){


        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }



    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){

     List<EmployeeDto> allList=new ArrayList<>();
     allList.addAll(employeeService.getAllEmployees());


     return new ResponseEntity<>(allList,HttpStatus.OK);


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable("id") Long id, @RequestBody EmployeeDto updatedEmployeeobj) {
        EmployeeDto updatedEmployee = employeeService.updateEmployeeById(id,updatedEmployeeobj);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable("id")Long id){
     Boolean delete=employeeService.deleteEmployeeById(id);

       return ResponseEntity.ok(delete);

    }

}
