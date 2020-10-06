package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.entities.Employee;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.mappers.EmployeesMapper;
import com.example.MySpringApplicationWithDB.repositories.DepartmentRepository;
import com.example.MySpringApplicationWithDB.repositories.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeesMapper employeesMapper;

    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, EmployeesMapper employeesMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.employeesMapper = employeesMapper;
    }

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream().map(employeesMapper::fromEmployee).collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        return employeesMapper.fromEmployee(employee);

    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeesMapper.toEmployee(employeeDto);
        employeeRepository.save(employee);
        employeeDto.setId(employee.getId());
        return employeeDto;
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) throws NotFoundException, IllegalArgumentException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        if (StringUtils.isNoneBlank(employeeDto.getName())) {
            employee.setName(employeeDto.getName());
        }
        if (StringUtils.isNoneBlank(employeeDto.getSurname())) {
            employee.setName(employeeDto.getName());
        }
        if (StringUtils.isNoneBlank(employeeDto.getPosition())) {
            employee.setName(employeeDto.getPosition());
        }
        if (StringUtils.isNoneBlank(employeeDto.getMail())) {
            employee.setName(employeeDto.getMail());
        }
        if (employeeDto.getDepartmentDto().getId() != null) {
            Department department = departmentRepository.findById(employeeDto.getDepartmentDto().getId()).orElseThrow(() -> new NotFoundException("dfd"));
            employee.setDepartment(department);
        }
        employeeRepository.save(employee);
        employeeDto.setId(employee.getId());
        return employeeDto;
    }

    @Transactional
    public void deleteEmployee(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

}
