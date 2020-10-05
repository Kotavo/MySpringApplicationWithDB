package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    void setUp() {
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
    void findALlEmployees() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        Department department = departmentsMapper.toDepartment(departmentDto);
        department.setId(1L);
        EmployeeDto employeeDto = new EmployeeDto().toBuilder()
                .id(1L)
                .name("empName")
                .surname("empSurname")
                .position("empPosition")
                .mail("empMail")
                .departmentDto(departmentDto).build();
        Employee employee = employeesMapper.toEmployee(employeeDto);
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeList.add(employee);
        employeeDtoList.add(employeeDto);
        List<EmployeeDto> found;
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        found = employeeService.findAllEmployees();
        assertThat(found).isEqualTo(employeeDtoList);
    }

    @Test
    void findEmployeeById() throws NotFoundException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        EmployeeDto employeeDto = new EmployeeDto().toBuilder()
                .id(1L)
                .name("empName")
                .surname("empSurname")
                .position("empPosition")
                .mail("empMail")
                .departmentDto(departmentDto).build();
        EmployeeDto found = employeeService.findEmployeeById(1L);
        assertThat(found).isEqualTo(employeeDto);
    }

    @Test
    void updateEmployee() throws NotFoundException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("newDepName");
        departmentDto.setLocation("newDepLoc");
        EmployeeDto toUpdate = new EmployeeDto().toBuilder()
                .name("newEmpName")
                .surname("newEmpSurname")
                .position("newEmpPosition")
                .mail("newEmpMail")
                .departmentDto(departmentDto).build();
        Employee employee = employeesMapper.toEmployee(toUpdate);
        Employee mockReturn = employeesMapper.toEmployee(toUpdate);
        mockReturn.setId(1L);
        Mockito.when(employeeRepository.save(employee)).thenReturn(mockReturn);
        EmployeeDto result = employeeService.updateEmployee(1L, toUpdate);
        assertThat(result).isEqualTo(toUpdate);
    }

    @Test
    void deleteEmployee() throws NotFoundException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("DepName");
        departmentDto.setLocation("DepLoc");
        EmployeeDto dtoToDelete = new EmployeeDto().toBuilder()
                .id(1L)
                .name("EmpName")
                .surname("EmpSurname")
                .position("EmpPosition")
                .mail("empMail")
                .departmentDto(departmentDto).build();
        Employee empToDelete = employeesMapper.toEmployee(dtoToDelete);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(empToDelete));
        employeeService.deleteEmployee(1L);
        assertThat(empToDelete.isDeleted()).isTrue();
    }
}
