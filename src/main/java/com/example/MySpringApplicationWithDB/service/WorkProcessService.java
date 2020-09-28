package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.EmployeeRepository;
import com.example.MySpringApplicationWithDB.repository.WorkProcessRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkProcessService {

    private final WorkProcessRepository workProcessRepository;
    private final EmployeeRepository employeeRepository;


    public WorkProcessService(WorkProcessRepository workProcessRepository, EmployeeRepository employeeRepository) {
        this.workProcessRepository = workProcessRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<WorkProcessDto> findAllWorkProcesses() {
        return workProcessRepository.findAll().stream().map(WorkProcessDto::new).collect(Collectors.toList());
    }

    public WorkProcessDto findWorkProcessById(Long id) throws NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        return new WorkProcessDto(workProcess);
    }

    @Transactional
    public WorkProcessDto createWorkProcess(WorkProcessDto workProcessDto) throws NotFoundException {
        if (!workProcessDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + workProcessDto.toString());
        } else {
            WorkProcess workProcess = new WorkProcess(workProcessDto);
            Employee employee = employeeRepository.findById(workProcessDto.getEmployeeDto().getId()).orElseThrow(() -> new NotFoundException("dfd"));
            workProcess.setEmployee(employee);
            workProcessRepository.save(workProcess);
            workProcessDto.setId(workProcess.getId());
            return workProcessDto;
        }
    }

    @Transactional
    public WorkProcessDto updateWorkProcess(Long id, WorkProcessDto workProcessDto) throws IllegalArgumentException, NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        if (StringUtils.isNoneBlank(workProcessDto.getDescription())) {
            workProcess.setDescription(workProcessDto.getDescription());
        }
        if (workProcessDto.getEmployeeDto().getId() != null) {
            Employee employee = employeeRepository.findById(workProcessDto.getEmployeeDto().getId()).orElseThrow(() -> new NotFoundException("dfd"));
            workProcess.setEmployee(employee);
        }
        workProcessRepository.save(workProcess);
        workProcessDto.setId(workProcess.getId());
        return workProcessDto;
    }

    @Transactional
    public void deleteWorkProcess(Long id) throws NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        workProcess.setDeleted(true);
        workProcessRepository.save(workProcess);
    }
}
