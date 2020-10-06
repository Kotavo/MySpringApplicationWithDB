package com.example.MySpringApplicationWithDB.repositories;

import com.example.MySpringApplicationWithDB.entities.Department;
import org.junit.jupiter.api.*;
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
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("When Department created then department.id > 0L")
    @Rollback(value = false)
    @Order(1)
    void createDepartment() {
        Department department = new Department();
        department.setName("name");
        department.setLocation("location");
        departmentRepository.save(department);
        assertThat(department.getId()).isNotNull().isGreaterThan(0L);
    }

    @Test
    @DisplayName("When find Department then return Department")
    @Order(2)
    void findDepartmentById() {
        Optional<Department> OptDep = departmentRepository.findById(1L);
        Department department = new Department();
        if (OptDep.isPresent()) {
            department = OptDep.get();
        }
        assertThat(department.getName()).isEqualTo("name");
    }


    @Test
    @DisplayName("When find All Department then return list.size > 0")
    @Order(3)
    void findAllDepartments() {
        List<Department> foundDepartments = departmentRepository.findAll();
        assertThat(foundDepartments.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("When Department updated then return new name and location")
    @Order(4)
    void updateDepartmentById() {
        Optional<Department> depToUpdateOptional = departmentRepository.findById(1L);
        Department depToUpdate = depToUpdateOptional.get();
        depToUpdate.setName("newName");
        depToUpdate.setLocation("newLocation");
        departmentRepository.save(depToUpdate);
        Optional<Department> depAfterUpdateOptional = departmentRepository.findById(1L);
        Department depAfterUpdate = depAfterUpdateOptional.get();
        assertThat(depAfterUpdate.getName()).isEqualTo("newName");
        assertThat(depAfterUpdate.getLocation()).isEqualTo("newLocation");
    }

    @Test
    @DisplayName("When Department deleted then return findById = null")
    @Order(5)
    void deleteDepartmentById() {
        Optional<Department> OptDep = departmentRepository.findById(1L);
        Department department = OptDep.get();
        departmentRepository.deleteById(department.getId());
        Optional<Department> OptDepDeleted = departmentRepository.findById(1L);
        assertThat(OptDepDeleted.isPresent()).isFalse();
    }

}

