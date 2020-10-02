package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeesMapper employeesMapper;
    @MockBean
    private static DepartmentsMapper departmentsMapper;

    private static EmployeeDto employeeDto;
    private static Employee employee;
    private static DepartmentDto departmentDto;
    private static Department department;


    @BeforeAll
    static void init() {
        departmentDto = new DepartmentDto(1L, "depName", "depLocation", null);
        department = new Department(1L, "depName", "depLocation", null, false);
        employeeDto = new EmployeeDto().toBuilder()
                .id(1L)
                .name("name")
                .surname("surname")
                .position("position")
                .mail("mail")
                .departmentDto(departmentDto)
                .build();
        employee = new Employee().toBuilder()
                .id(1L)
                .name("name")
                .surname("surname")
                .position("position")
                .mail("mail")
                .department(department)
                .deleted(false)
                .build();
    }

    @BeforeEach
    void setUP() {
        Mockito.when(departmentsMapper.toDepartment(departmentDto)).thenReturn(department);
        Mockito.when(departmentsMapper.fromDepartment(department)).thenReturn(departmentDto);
    }

    @Test
    void toEmployee() {
        Employee employeeFromDto = employeesMapper.toEmployee(employeeDto);
        assertThat(employeeFromDto.getId()).isEqualTo(employeeDto.getId());
        assertThat(employeeFromDto.getName()).isEqualTo(employeeDto.getName());
        assertThat(employeeFromDto.getSurname()).isEqualTo(employeeDto.getSurname());
        assertThat(employeeFromDto.getPosition()).isEqualTo(employeeDto.getPosition());
        assertThat(employeeFromDto.getMail()).isEqualTo(employeeDto.getMail());
        assertThat(employeeFromDto.getDepartment().getId()).isEqualTo(employeeDto.getDepartmentDto().getId());
        assertThat(employeeFromDto.getDepartment().getName()).isEqualTo(employeeDto.getDepartmentDto().getName());
        assertThat(employeeFromDto.getDepartment().getLocation()).isEqualTo(employeeDto.getDepartmentDto().getLocation());
    }

    @Test
    void fromEmployee() {
        EmployeeDto dtoFromEmployee = employeesMapper.fromEmployee(employee);
        System.out.println(employee);
        System.out.println(dtoFromEmployee);
        assertThat(dtoFromEmployee.getId()).isEqualTo(employee.getId());
        assertThat(dtoFromEmployee.getName()).isEqualTo(employee.getName());
        assertThat(dtoFromEmployee.getSurname()).isEqualTo(employee.getSurname());
        assertThat(dtoFromEmployee.getPosition()).isEqualTo(employee.getPosition());
        assertThat(dtoFromEmployee.getMail()).isEqualTo(employee.getMail());
        assertThat(dtoFromEmployee.getDepartmentDto().getId()).isEqualTo(employee.getDepartment().getId());
        assertThat(dtoFromEmployee.getDepartmentDto().getName()).isEqualTo(employee.getDepartment().getName());
        assertThat(dtoFromEmployee.getDepartmentDto().getLocation()).isEqualTo(employee.getDepartment().getLocation());
    }

}
