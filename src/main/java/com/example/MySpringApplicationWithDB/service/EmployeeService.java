package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> findAllEmployee() {
        return employeeRepository.findAll().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    public EmployeeDto findById(Long id) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new EmployeeDto(employee.get());
        } else {
            throw new NotFoundException("Employee ID=" + id + " not found");
        }
    }

    @Transactional
    @Modifying
    public void saveEmployee(EmployeeDto employeeDto) throws IllegalArgumentException {
        if (!employeeDto.isValid()) {
            throw new IllegalArgumentException("Employee is not valid " + employeeDto.toString());
        } else {
            employeeRepository.save(new Employee(employeeDto));
        }
    }

    @Transactional
    @Modifying
    public void updateEmployee(Long id, EmployeeDto employeeDto) throws NotFoundException, IllegalArgumentException{
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee ID=" + id + " not found");
        }
        if(!employeeDto.isValid()){
            throw new IllegalArgumentException("Employee is not valid " + employeeDto.toString());
        }
        else {
            Employee EmployeeToSave = new Employee()
                    .toBuilder().id(id)
                    .name(employeeDto.getName())
                    .surname(employeeDto.getSurname())
                    .position(employeeDto.getPosition())
                    .mail(employeeDto.getMail())
                    .department(employeeDto.getDepartment()).build();
            employeeRepository.save(EmployeeToSave);
        }
    }

    @Transactional
    @Modifying
    public void deleteEmployee(Long id) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee ID=" + id + " not found");
        } else {
            Employee employeeToSave = employee.get();
            employeeToSave.setDeleted(true);
            employeeRepository.save(employeeToSave);
        }
    }

}
