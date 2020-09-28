package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.enity.Department;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.DepartmentRepository;
import com.example.MySpringApplicationWithDB.repository.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        return new EmployeeDto(employee);

    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws IllegalArgumentException, NotFoundException {
        if (!employeeDto.isValid()) {
            throw new IllegalArgumentException("Employee is not valid " + employeeDto.toString());
        } else {
            Employee employee = new Employee(employeeDto);
            Department department = departmentRepository.findById(employeeDto.getDepartmentDto().getId()).orElseThrow(() -> new NotFoundException("dfd"));
            employee.setDepartment(department);
            employeeRepository.save(employee);
            employeeDto.setId(employee.getId());
            return employeeDto;
        }
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
    @Modifying
    public void deleteEmployee(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

}
