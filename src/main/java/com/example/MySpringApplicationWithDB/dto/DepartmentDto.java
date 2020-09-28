package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private String location;

    private List<EmployeeDto> employeesDto;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.location = department.getLocation();
  //      this.employeesDto = department.getEmployees().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Transient
    public boolean isValid() {
        return !name.isEmpty() && !location.isEmpty();

    }

}
