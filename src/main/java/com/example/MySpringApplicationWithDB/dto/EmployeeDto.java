package com.example.MySpringApplicationWithDB.dto;

import com.example.MySpringApplicationWithDB.enity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private String position;

    private String mail;

    private DepartmentDto departmentDto;  // ? Department department ?

    private WorkProcessDto workProcessDto;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.position = employee.getPosition();
        this.mail = employee.getMail();
        this.departmentDto = new DepartmentDto(employee.getDepartment());
    //    this.workProcessDto = new WorkProcessDto(employee.getWorkProcess());
    }

    @Transient
    public boolean isValid() {
        return !name.isEmpty() &&
                !position.isEmpty() &&
                departmentDto.getId() != null;

    }
}
