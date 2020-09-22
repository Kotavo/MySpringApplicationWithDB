package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.Employee;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.WorkProcessRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkProcessService {

    private final WorkProcessRepository workProcessRepository;


    public WorkProcessService(WorkProcessRepository workProcessRepository) {
        this.workProcessRepository = workProcessRepository;
    }

    public List<WorkProcessDto> findAllWorkProcesses() {
        return workProcessRepository.findAll().stream().map(WorkProcessDto::new).collect(Collectors.toList());
    }

    public WorkProcessDto findWorkProcessById(Long id) throws NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        return new WorkProcessDto(workProcess);
    }

    @Transactional
    @Modifying
    public WorkProcessDto createWorkProcess(WorkProcessDto workProcessDto) {
        if (!workProcessDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + workProcessDto.toString());
        } else {
            WorkProcess workProcess = new WorkProcess(workProcessDto);
            workProcessRepository.save(workProcess);
            workProcessDto.setId(workProcess.getId());
            return workProcessDto;
        }
    }

    @Transactional
    @Modifying
    public WorkProcessDto updateWorkProcess(Long id, WorkProcessDto workProcessDto) throws IllegalArgumentException, NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        if (StringUtils.isNoneBlank(workProcessDto.getDescription())) {
            workProcess.setDescription(workProcessDto.getDescription());
        }
        if (workProcessDto.getEmployeeDto() != null) {
            workProcess.setEmployee(new Employee(workProcessDto.getEmployeeDto()));
        }
        workProcessRepository.save(workProcess);
        workProcessDto.setId(workProcess.getId());
        return workProcessDto;
    }

    @Transactional
    @Modifying
    public void deleteWorkProcess(Long id) throws NotFoundException {
        WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new NotFoundException("Work Process ID=" + id + " not found"));
        workProcess.setDeleted(true);
        workProcessRepository.save(workProcess);
    }
}
