package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.mappers.DepartmentsMapper;
import com.example.MySpringApplicationWithDB.mappers.EmployeesMapper;
import com.example.MySpringApplicationWithDB.repositories.DepartmentRepository;
import com.example.MySpringApplicationWithDB.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private EmployeeRepository employeeRepository;


    @BeforeEach
    void setUp(){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        Department department = departmentsMapper.toDepartment(departmentDto);
        department.setId(1L);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.of(department));
        EmployeeDto employeeDto = new EmployeeDto().toBuilder()
                .name("empName")
                .surname("empSurname")
                .position("empPosition")
                .mail("empMail")
                .departmentDto(departmentDto).build();
        Employee employee = employeesMapper.toEmployee(employeeDto);
        employee.setId(1L);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
    }

    @Test
    void findALlEmployees(){

    }

}
