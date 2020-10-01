package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.mappers.DepartmentsMapper;
import com.example.MySpringApplicationWithDB.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentsMapper departmentsMapper;

    public List<DepartmentDto> findAllDepartments() {
        return departmentRepository.findAll().stream().map(departmentsMapper::fromDepartment).collect(Collectors.toList());
    }

    public DepartmentDto findDepartmentById(Long id) throws NotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department ID=" + id + " not found"));
        return departmentsMapper.fromDepartment(department);
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = departmentsMapper.toDepartment(departmentDto);
        department = departmentRepository.save(department);
        departmentDto.setId(department.getId());
        return departmentDto;
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
