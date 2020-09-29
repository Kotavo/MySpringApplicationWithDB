package com.example.MySpringApplicationWithDB.mappers;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.enity.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EmployeesMapper.class)
public interface DepartmentsMapper {
    DepartmentsMapper DEPARTMENTS_MAPPER = Mappers.getMapper(DepartmentsMapper.class);

    @Mapping(target = "employeesDto", source = "employees", ignore = true)
    DepartmentDto fromDepartment(Department department);

    @InheritInverseConfiguration
    Department toDepartment(DepartmentDto departmentDto);
}
