package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.enity.Department;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.DepartmentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> findAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentDto::new).collect(Collectors.toList());
    }

    public DepartmentDto findById(Long id) throws NotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return new DepartmentDto(department.get());
        } else {
            throw new NotFoundException("Department ID=" + id + " not found");
        }
    }

    @Transactional
    @Modifying
    public void saveDepartment(DepartmentDto departmentDto) throws IllegalArgumentException {
        if (!departmentDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + departmentDto.toString());
        } else {
            departmentRepository.save(new Department(departmentDto));
        }
    }

    @Transactional
    @Modifying
    public void updateDepartment(Long id, DepartmentDto departmentDto) throws NotFoundException, IllegalArgumentException {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new NotFoundException("Department ID=" + id + " not found");
        }
        if(!departmentDto.isValid()){
            throw new IllegalArgumentException("Department is not valid " + departmentDto.toString());
        }
        else {
            Department departmentToSave = new Department(id, departmentDto.getName(), departmentDto.getLocation(), false);
            departmentRepository.save(departmentToSave);
        }
    }

    @Transactional
    @Modifying
    public void deleteDepartment(Long id) throws NotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new NotFoundException("Department ID=" + id + " not found");
        } else {
            Department departmentToSave = department.get();
            departmentToSave.setDeleted(true);
            departmentRepository.save(departmentToSave);
        }
    }
}
