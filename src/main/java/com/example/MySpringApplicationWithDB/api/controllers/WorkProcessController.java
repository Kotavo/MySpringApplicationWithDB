package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.WorkProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkProcessController {

    private final WorkProcessService workProcessService;


    public WorkProcessController(WorkProcessService workProcessService) {
        this.workProcessService = workProcessService;
    }

    @GetMapping("/workProcesses")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<WorkProcessDto> getWorkProcesses() {
        return workProcessService.findAllWorkProcesses();
    }


    @GetMapping("workProcess/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public WorkProcessDto getWorkProcess(@PathVariable Long id) throws NotFoundException {
        return workProcessService.findWorkProcessById(id);
    }

    @PostMapping("/workProcess")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createWorkProcess(@RequestBody WorkProcessDto workProcessDto) {
        workProcessService.createWorkProcess(workProcessDto);
    }

    @PutMapping("/workProcess/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateWorkProcess(@PathVariable Long id, @RequestBody WorkProcessDto workProcessDto) throws NotFoundException {
        workProcessService.updateWorkProcess(id, workProcessDto);
    }

    @DeleteMapping("/workProcess/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteWorkProcess(@PathVariable Long id) throws NotFoundException {
        workProcessService.deleteWorkProcess(id);
    }
}
