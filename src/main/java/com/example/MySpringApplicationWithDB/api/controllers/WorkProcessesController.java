package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.WorkProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkProcessesController {

    private final WorkProcessService workProcessService;

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
    public ResponseEntity<?> createWorkProcess(@RequestBody WorkProcessDto workProcessDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workProcessService.createWorkProcess(workProcessDto));
    }

    @PutMapping("/workProcess/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<WorkProcessDto> updateWorkProcess(@PathVariable Long id, @RequestBody WorkProcessDto workProcessDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(workProcessService.updateWorkProcess(id, workProcessDto));
    }

    @DeleteMapping("/workProcess/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteWorkProcess(@PathVariable Long id) throws NotFoundException {
        workProcessService.deleteWorkProcess(id);
    }
}
