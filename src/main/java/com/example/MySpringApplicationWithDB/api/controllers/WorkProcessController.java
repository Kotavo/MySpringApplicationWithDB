package com.example.MySpringApplicationWithDB.api.controllers;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.service.WorkProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/WorkProcess")
public class WorkProcessController {

    private final WorkProcessService workProcessService;


    public WorkProcessController(WorkProcessService workProcessService) {
        this.workProcessService = workProcessService;
    }

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<WorkProcessDto> findAll() {
        return workProcessService.findAllWorkProcess();
    }


    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public WorkProcessDto findById(@PathVariable Long id) throws NotFoundException {
        return workProcessService.findById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody WorkProcessDto workProcessDto) {
        workProcessService.saveWorkProcess(workProcessDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateById(@PathVariable Long id, @RequestBody WorkProcessDto workProcessDto) throws NotFoundException {
        workProcessService.updateWorkProcess(id, workProcessDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@RequestBody Long id) throws NotFoundException {
        workProcessService.deleteWorkProcess(id);
    }
}
