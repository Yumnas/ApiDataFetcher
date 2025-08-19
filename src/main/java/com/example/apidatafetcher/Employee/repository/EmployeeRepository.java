package com.example.apidatafetcher.Employee.repository;

import com.example.apidatafetcher.Employee.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //updating salary by department
    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET salary = salary + 5000 WHERE department = :dept", nativeQuery = true)
    int increaseSalaryByDepartment(@Param("dept") String department);
}

