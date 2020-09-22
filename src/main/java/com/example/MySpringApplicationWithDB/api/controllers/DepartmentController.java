package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<DepartmentDto> getDepartments(){
        return departmentService.findAllDepartments();
    }

    @GetMapping("department/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    //ResponceEntity
    public DepartmentDto getDepartment(@PathVariable Long id) throws NotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @PostMapping("/department")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createDepartment(@RequestBody DepartmentDto departmentDto){
        departmentService.createDepartment(departmentDto);
    }

    @PutMapping("/department/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) throws NotFoundException {
        departmentService.updateDepartment(id, departmentDto);
    }

    @DeleteMapping("/department/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteDepartment(@PathVariable Long id) throws NotFoundException {
        departmentService.deleteDepartment(id);
    }
}
