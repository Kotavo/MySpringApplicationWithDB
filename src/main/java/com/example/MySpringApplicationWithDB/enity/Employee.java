package com.example.MySpringApplicationWithDB.enity;


import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "employees")
@Where(clause = "deleted = false")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "employee")
    private WorkProcess workProcess;

    private boolean deleted = false;

}
