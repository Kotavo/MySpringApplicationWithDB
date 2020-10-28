package com.example.MySpringApplicationWithDB.repositories;

import com.example.MySpringApplicationWithDB.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByMail(String mail);
}
