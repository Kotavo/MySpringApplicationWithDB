package com.example.MySpringApplicationWithDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    private DepartmentDto departmentDto;

    private WorkProcessDto workProcessDto;

}
