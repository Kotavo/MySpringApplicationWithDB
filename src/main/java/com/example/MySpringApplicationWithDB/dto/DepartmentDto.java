package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private String location;

    public DepartmentDto(Department department){
        this.id = department.getId();
        this.name = department.getName();
        this.location = department.getLocation();
    }

}
