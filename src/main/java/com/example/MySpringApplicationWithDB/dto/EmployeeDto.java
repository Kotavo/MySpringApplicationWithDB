package com.example.MySpringApplicationWithDB.dto;

import lombok.*;

@Data
@EqualsAndHashCode(exclude = "workProcessDto")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    private DepartmentDto departmentDto;

    private WorkProcessDto workProcessDto;

}
