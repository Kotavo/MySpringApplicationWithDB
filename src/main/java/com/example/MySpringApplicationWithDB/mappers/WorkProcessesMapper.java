package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EmployeesMapper.class)
public interface WorkProcessesMapper {

    @Mapping(target = "employeeDto", source = "employee")
    WorkProcessDto fromWorkProcess(WorkProcess workProcess);

    @InheritInverseConfiguration
    WorkProcess toWorkProcess(WorkProcessDto workProcessDto);

}
