package com.example.MySpringApplicationWithDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private String location;

    private List<EmployeeDto> employeesDto;

}
