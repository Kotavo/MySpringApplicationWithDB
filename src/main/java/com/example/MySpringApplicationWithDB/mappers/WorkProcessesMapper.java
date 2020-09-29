package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.WorkProcessDto;
import com.example.MySpringApplicationWithDB.enity.WorkProcess;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EmployeesMapper.class)
public interface WorkProcessesMapper {

 //   WorkProcessesMapper WORK_PROCESSES_MAPPER = Mappers.getMapper(WorkProcessesMapper.class);

    @Mapping(target = "employeeDto", source = "employee")
    WorkProcessDto fromWorkProcess(WorkProcess workProcess);

    @InheritInverseConfiguration
    WorkProcess toWorkProcess(WorkProcessDto workProcessDto);

}
