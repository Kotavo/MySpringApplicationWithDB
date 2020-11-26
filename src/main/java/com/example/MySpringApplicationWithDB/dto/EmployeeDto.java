package com.example.MySpringApplicationWithDB.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private String password;

    private DepartmentDto departmentDto;

    private WorkProcessDto workProcessDto;

}
