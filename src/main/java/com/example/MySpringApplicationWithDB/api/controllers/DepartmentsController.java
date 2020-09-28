package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentsController {

    private final DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("department/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(departmentService.findDepartmentById(id));
    }

/*    @GetMapping("department/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public DepartmentDto getDepartment(@PathVariable Long id) throws NotFoundException {
        return departmentService.findDepartmentById(id);
    }*/


    @PostMapping("/department")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(departmentDto));
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(id, departmentDto));
    }

//    @PutMapping("/department/{id}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public void updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) throws NotFoundException {
//        departmentService.updateDepartment(id, departmentDto);
//    }

    @DeleteMapping("/department/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteDepartment(@PathVariable Long id) throws NotFoundException {
        departmentService.deleteDepartment(id);
    }
}