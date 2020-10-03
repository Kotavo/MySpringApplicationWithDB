package com.example.MySpringApplicationWithDB.repositories;

import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.entities.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    void createEmployee() {
        Department department = new Department();
        department.setId(1L);
        department.setName("name");
        department.setLocation("location");
        departmentRepository.save(department);
        Employee employee = new Employee().toBuilder()
                .name("name")
                .surname("surname")
                .position("position")
                .mail("mail")
                .department(department).build();
        employeeRepository.save(employee);
        assertThat(employee.getId()).isNotNull().isGreaterThan(0L);
    }

    @Test
    @Order(2)
    void findDepartmentById() {
        Optional<Employee> OptEmployee = employeeRepository.findById(1L);
        Employee employee = new Employee();
        if (OptEmployee.isPresent()) {
            employee = OptEmployee.get();
        }
        assertThat(employee.getName()).isEqualTo("name");
    }

    @Test
    @Order(3)
    void findAllDepartment() {
        List<Employee> foundEmployee = employeeRepository.findAll();
        assertThat(foundEmployee.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    void updateDepartmentById() {
        Optional<Employee> empToUpdateOptional = employeeRepository.findById(1L);
        Department newDep = new Department();
        newDep.setId(2L);
        newDep.setName("newDepName");
        newDep.setLocation("newDepLoc");
        departmentRepository.save(newDep);
        Employee empToUpdate = empToUpdateOptional.get();
        empToUpdate.setName("newName");
        empToUpdate.setSurname("newSurname");
        empToUpdate.setPosition("newPosition");
        empToUpdate.setDepartment(newDep);
        employeeRepository.save(empToUpdate);
        Optional<Employee> empAfterUpdateOptional = employeeRepository.findById(1L);
        Employee empAfterUpdate = empAfterUpdateOptional.get();
        assertThat(empAfterUpdate.getName()).isEqualTo("newName");
        assertThat(empAfterUpdate.getSurname()).isEqualTo("newSurname");
        assertThat(empAfterUpdate.getPosition()).isEqualTo("newPosition");
        assertThat(empAfterUpdate.getDepartment().getName()).isEqualTo("newDepName");
        assertThat(empAfterUpdate.getDepartment().getLocation()).isEqualTo("newDepLoc");
    }

    @Test
    @Order(5)
    void deleteDepartmentById() {
        Optional<Employee> OptEmp = employeeRepository.findById(1L);
        Employee employee = OptEmp.get();
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> OptEmpDeleted = employeeRepository.findById(1L);
        assertThat(OptEmpDeleted.isPresent()).isFalse();
    }
}
