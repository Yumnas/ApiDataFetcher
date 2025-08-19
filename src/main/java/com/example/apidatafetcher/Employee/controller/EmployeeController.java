package com.example.apidatafetcher.Employee.controller;

import com.example.apidatafetcher.Employee.entity.Employee;
import com.example.apidatafetcher.Employee.service.EmployeeService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.addEmployees(employee));
    }
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @PatchMapping("/UpdateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id){
        return ResponseEntity.ok(employeeService.updatingEmployee(employee, id));
    }
    @DeleteMapping("/DeleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        return ResponseEntity.ok(employeeService.deletingEmployee(id));
    }
    @PutMapping("/IncreasingEmployeeSalaryByDept/{dept}")
    public ResponseEntity<String> increaseSalaryByDept(@PathVariable String dept){
        int rowsUpdated = employeeService.raiseSalary(dept);
        return ResponseEntity.ok(rowsUpdated + " employees got a raise in " + dept + " department!");
    }



}
