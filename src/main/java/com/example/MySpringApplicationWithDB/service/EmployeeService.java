package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.enity.Department;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.mappers.EmployeesMapper;
import com.example.MySpringApplicationWithDB.repository.DepartmentRepository;
import com.example.MySpringApplicationWithDB.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeesMapper.EMPLOYEES_MAPPER::fromEmployee).collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee ID=" + id + " not found"));
        return EmployeesMapper.EMPLOYEES_MAPPER.fromEmployee(employee);

    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeesMapper.EMPLOYEES_MAPPER.toEmployee(employeeDto);
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
