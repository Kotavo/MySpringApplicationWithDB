package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<EmployeeDto> getEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("employee/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public EmployeeDto getEmployee(@PathVariable Long id) throws NotFoundException {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeDto));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(id, employeeDto));
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) throws NotFoundException {
        employeeService.deleteEmployee(id);
    }

}
