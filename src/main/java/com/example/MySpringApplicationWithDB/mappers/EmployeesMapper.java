package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.EmployeeDto;
import com.example.MySpringApplicationWithDB.enity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DepartmentsMapper.class, WorkProcessesMapper.class})
public interface EmployeesMapper {

    @Mapping(target = "departmentDto", source = "department")
    @Mapping(target = "workProcessDto", source = "workProcess", ignore = true)
    EmployeeDto fromEmployee(Employee employee);

    @InheritInverseConfiguration
    Employee toEmployee(EmployeeDto employeeDto);


}
