package com.example.MySpringApplicationWithDB.enity;


import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "employees")
@Where(clause = "isDeleted = false")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    private Long department;

    private boolean isDeleted = false;

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
        this.surname = employeeDto.getSurname();
        this.position = employeeDto.getPosition();
        this.mail = employeeDto.getMail();
        this.department = employeeDto.getDepartment();
    }


}
