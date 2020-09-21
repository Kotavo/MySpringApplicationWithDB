package com.example.MySpringApplicationWithDB.enity;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
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
@Where(clause = "isDeleted = false")
public class WorkProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long employee;

    private boolean isDeleted = false;

    public WorkProcess(WorkProcessDto workProcessDto){
        this.description = workProcessDto.getDescription();
        this.employee = workProcessDto.getEmployee();
    }


}
