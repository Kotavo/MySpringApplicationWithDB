package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentsController {

    private final DepartmentService departmentService;

    @GetMapping("/departments")
    @ResponseStatus(code = HttpStatus.FOUND)
    @PreAuthorize("hasRole('USER')")
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("department/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getDepartment(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(departmentService.findDepartmentById(id));
    }

    @PostMapping("/department")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(departmentDto));
    }

    @PutMapping("/department/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(id, departmentDto));
    }

    @DeleteMapping("/department/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDepartment(@PathVariable Long id) throws NotFoundException {
        departmentService.deleteDepartment(id);
    }
}
