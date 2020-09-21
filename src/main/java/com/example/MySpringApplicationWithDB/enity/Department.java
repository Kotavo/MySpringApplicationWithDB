package com.example.MySpringApplicationWithDB.enity;


import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Long id;

    private String name;

    private String location;

    public Department(DepartmentDto departmentDto) {
        this.name = departmentDto.getName();
        this.location = departmentDto.getLocation();

    }

}
