package com.example.MySpringApplicationWithDB.service;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.repository.WorkProcessRepository;
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

    public List<WorkProcessDto> findAllWorkProcess() {
        return workProcessRepository.findAll().stream().map(WorkProcessDto::new).collect(Collectors.toList());
    }

    public WorkProcessDto findById(Long id) throws NotFoundException {
        Optional<WorkProcess> workProcess = workProcessRepository.findById(id);
        if (workProcess.isPresent()) {
            return new WorkProcessDto(workProcess.get());
        } else {
            throw new NotFoundException("Work Process ID=" + id + " not found");
        }
    }

    @Transactional
    @Modifying
    public void saveWorkProcess(WorkProcessDto workProcessDto) {
        if (!workProcessDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + workProcessDto.toString());
        } else {
            workProcessRepository.save(new WorkProcess(workProcessDto));
        }
    }

    @Transactional
    @Modifying
    public void updateWorkProcess(Long id, WorkProcessDto workProcessDto) throws IllegalArgumentException, NotFoundException {
        Optional<WorkProcess> workProcess = workProcessRepository.findById(id);
        if (workProcess.isEmpty()) {
            throw new NotFoundException("Work Process ID=" + id + " not found");
        }
        if (!workProcessDto.isValid()) {
            throw new IllegalArgumentException("Department is not valid " + workProcessDto.toString());
        } else {
            workProcessRepository.save(new WorkProcess(id, workProcessDto.getDescription(), workProcessDto.getEmployee(), false));
        }
    }

    @Transactional
    @Modifying
    public void deleteWorkProcess(Long id) throws NotFoundException {
        Optional<WorkProcess> workProcess = workProcessRepository.findById(id);
        if (workProcess.isEmpty()) {
            throw new NotFoundException("Work Process ID=" + id + " not found");
        } else {
            WorkProcess workProcessToSave = workProcess.get();
            workProcessToSave.setDeleted(true);
            workProcessRepository.save(workProcessToSave);
        }
    }

}
