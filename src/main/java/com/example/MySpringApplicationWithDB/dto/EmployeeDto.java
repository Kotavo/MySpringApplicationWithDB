package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    private Long department;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.position = employee.getPosition();
        this.mail = employee.getMail();
        this.department = employee.getDepartment();
    }

    @Transient
    public boolean isValid() {
        return !name.isEmpty() &&
               !surname.isEmpty() &&
               !position.isEmpty() &&
               !mail.isEmpty() &&
               department != null;

    }
}
