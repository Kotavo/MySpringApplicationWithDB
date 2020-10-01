package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.JUnitException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentMapperTest {

    private static DepartmentsMapper departmentsMapper;
    private static DepartmentDto departmentDto;
    private static Department department;

    @BeforeAll
    static void setUp() {
        departmentsMapper = new DepartmentsMapperImpl();
        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Name");
        departmentDto.setLocation("Location");
        department = new Department();
        department.setId(1L);
        department.setName("Name");
        department.setLocation("Location");
    }

    @Test
    void toDepartment() {
        Department departmentFromDto = departmentsMapper.toDepartment(departmentDto);
        assertThat(departmentFromDto.getId()).isEqualTo(departmentDto.getId());
        assertThat(departmentFromDto.getName()).isEqualTo(departmentDto.getName());
        assertThat(departmentFromDto.getLocation()).isEqualTo(departmentDto.getLocation());
    }

    @Test
    void fromDepartment() {
        DepartmentDto dtoFromDepartment = departmentsMapper.fromDepartment(department);
        assertThat(dtoFromDepartment.getId()).isEqualTo(department.getId());
        assertThat(dtoFromDepartment.getName()).isEqualTo(department.getName());
        assertThat(dtoFromDepartment.getLocation()).isEqualTo(department.getLocation());
    }
}
