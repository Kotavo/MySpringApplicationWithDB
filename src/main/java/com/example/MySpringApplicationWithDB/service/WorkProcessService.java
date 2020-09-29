package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.mappers.WorkProcessesMapper;
import com.example.MySpringApplicationWithDB.repository.EmployeeRepository;
import com.example.MySpringApplicationWithDB.repository.WorkProcessRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkProcessService {

    private final WorkProcessRepository workProcessRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkProcessesMapper workProcessesMapper;

    public List<WorkProcessDto> findAllWorkProcesses() {
        return workProcessRepository.findAll().stream().map(workProcessesMapper::fromWorkProcess).collect(Collectors.toList());
    }

    public WorkProcessDto findWorkProcessById(Long id) throws NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        return workProcessesMapper.fromWorkProcess(workProcess);
    }

    @Transactional
    public WorkProcessDto createWorkProcess(WorkProcessDto workProcessDto) {
        WorkProcess workProcess = workProcessesMapper.toWorkProcess(workProcessDto);
        workProcessRepository.save(workProcess);
        workProcessDto.setId(workProcessDto.getId());
        return workProcessDto;
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
