package com.example.MySpringApplicationWithDB.enity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
@Where(clause = "deleted = false")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employees;

    private boolean deleted = false;
}
