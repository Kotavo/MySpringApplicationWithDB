package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<EmployeeDto> findAll(){
        return employeeService.findAllEmployee();
    }


    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public EmployeeDto findById(@PathVariable Long id) throws NotFoundException {
        return employeeService.findById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody EmployeeDto employeeDto){
        employeeService.saveEmployee(employeeDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateById(@PathVariable Long id, EmployeeDto employeeDto) throws NotFoundException {
        employeeService.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@RequestBody Long id) throws NotFoundException {
        employeeService.deleteEmployee(id);
    }

}
