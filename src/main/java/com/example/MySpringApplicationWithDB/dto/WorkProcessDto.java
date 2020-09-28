package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkProcessDto {

    private Long id;

    private String description;

    private EmployeeDto employeeDto;

    public WorkProcessDto(WorkProcess workProcess) {
        this.id = workProcess.getId();
        this.description = workProcess.getDescription();
        this.employeeDto = new EmployeeDto(workProcess.getEmployee());
    }

    @Transient
    public boolean isValid() {
        return !description.isEmpty() && employeeDto.getId() != null;
    }

}
