package com.example.MySpringApplicationWithDB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workprocesses")
@Where(clause = "deleted = false")
public class WorkProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private boolean deleted = false;

}
