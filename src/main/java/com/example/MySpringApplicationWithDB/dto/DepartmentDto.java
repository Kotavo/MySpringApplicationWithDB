package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DepartmentDto {

    private Long id;

    private String name;

    private String location;

    public DepartmentDto(Department department){
        this.id = department.getId();
        this.name = department.getName();
        this.location = department.getLocation();
    }
    @Transient
    public boolean isValid(){
       return !name.isEmpty() && !location.isEmpty();

    }

}
