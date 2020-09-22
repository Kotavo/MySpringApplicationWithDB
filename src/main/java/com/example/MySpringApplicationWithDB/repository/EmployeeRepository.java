package com.example.MySpringApplicationWithDB.repository;

import com.example.MySpringApplicationWithDB.enity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
