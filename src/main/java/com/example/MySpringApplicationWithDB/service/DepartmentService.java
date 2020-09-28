package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.enity.Department;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.DepartmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public DepartmentDto findDepartmentById(Long id) throws NotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department ID=" + id + " not found"));
        return new DepartmentDto(department);
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) throws IllegalArgumentException {
        if (!departmentDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + departmentDto.toString());
        } else {
            Department department = new Department(departmentDto);
            departmentRepository.save(department);
            departmentDto.setId(department.getId());
            return departmentDto;
        }
    }

    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) throws NotFoundException, IllegalArgumentException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department ID=" + id + " not found"));
        if (StringUtils.isNoneBlank(departmentDto.getName())) {
            department.setName(departmentDto.getName());
        }
        if (StringUtils.isNoneBlank(departmentDto.getLocation())) {
            department.setLocation(departmentDto.getLocation());
        }
/*        if(!departmentDto.getEmployeesDto().isEmpty()){
            department.setEmployees(departmentDto.getEmployeesDto().stream().map(Employee::new).collect(Collectors.toList()));
        }*/
        departmentRepository.save(department);
        departmentDto.setId(department.getId());
        return departmentDto;
    }

    @Transactional
    public void deleteDepartment(Long id) throws NotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department ID=" + id + " not found"));
        department.setDeleted(true);
        departmentRepository.save(department);
    }
}
