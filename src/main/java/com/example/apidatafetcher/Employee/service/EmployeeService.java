package com.example.apidatafetcher.Employee.service;

import com.example.apidatafetcher.Employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee addEmployees(Employee employees);
    Employee getEmployeeById(Long id);
    Employee updatingEmployee(Employee employee, long id);
    String deletingEmployee(long id);
    int raiseSalary(String department);

}
