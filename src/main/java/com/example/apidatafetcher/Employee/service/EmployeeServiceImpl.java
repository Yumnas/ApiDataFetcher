package com.example.apidatafetcher.Employee.service;

import com.example.apidatafetcher.Employee.entity.Employee;
import com.example.apidatafetcher.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployees(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updatingEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
        // Only update password if user provided a new one
        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        return employeeRepository.save(existingEmployee);
    }
    @Override
    public String deletingEmployee(long id) {
        employeeRepository.deleteById(id);
        return "Employee is deleted Successfully from the database for id" + id;
    }

    @Override
    public int raiseSalary(String department){
        return employeeRepository.increaseSalaryByDepartment(department);
    }
}
