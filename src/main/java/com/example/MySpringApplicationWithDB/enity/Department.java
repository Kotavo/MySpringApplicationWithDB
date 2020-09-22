package com.example.MySpringApplicationWithDB.enity;


import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
@Where(clause = "is_deleted = false")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;
    //manyToOne
    private boolean isDeleted = false;

    public Department(DepartmentDto departmentDto) {
        this.name = departmentDto.getName();
        this.location = departmentDto.getLocation();

    }

}
