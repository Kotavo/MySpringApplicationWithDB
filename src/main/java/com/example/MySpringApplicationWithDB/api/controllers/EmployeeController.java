package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<EmployeeDto> getEmployees(){
        return employeeService.findAllEmployees();
    }


    @GetMapping("employee/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public EmployeeDto getEmployee(@PathVariable Long id) throws NotFoundException {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.createEmployee(employeeDto);
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) throws NotFoundException {
        employeeService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) throws NotFoundException {
        employeeService.deleteEmployee(id);
    }

}
