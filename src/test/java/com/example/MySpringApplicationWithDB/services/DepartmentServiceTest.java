package com.example.MySpringApplicationWithDB.services;

import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.entities.Department;
import com.example.MySpringApplicationWithDB.exceptions.NotFoundException;
import com.example.MySpringApplicationWithDB.mappers.DepartmentsMapper;
import com.example.MySpringApplicationWithDB.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {

/*    @TestConfiguration
    static class DepartmentServiceTestContextConfiguration{

        @Bean
        public DepartmentService departmentService(){
            return new DepartmentService(departmentRepository(),departmentsMapper());
        }

        @Bean
        public DepartmentsMapper departmentsMapper(){
            return new DepartmentsMapperImpl();
        }

        @Bean
        public DepartmentRepository departmentRepository(){
            return Mockito.mock(DepartmentRepository.class);
        }
    }*/


    @Autowired
    private DepartmentsMapper departmentsMapper;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        Department department = departmentsMapper.toDepartment(departmentDto);
        department.setId(1L);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.of(department));
    }

    @Test
    void FindAllDepartments() {
        DepartmentDto departmentDto = new DepartmentDto();
        DepartmentDto departmentDto2 = new DepartmentDto();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        departmentDto.setId(1L);
        departmentDto2.setName("depName2");
        departmentDto2.setLocation("depLoc2");
        departmentDto2.setId(2L);
        departmentDtoList.add(departmentDto);
        departmentDtoList.add(departmentDto2);
        Department department = departmentsMapper.toDepartment(departmentDto);
        Department department1 = departmentsMapper.toDepartment(departmentDto2);
        departmentList.add(department);
        departmentList.add(department1);
        List<DepartmentDto> found;
        Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
        found = departmentService.findAllDepartments();
        assertThat(found).isEqualTo(departmentDtoList);
    }

    @Test
    void findDepartmentById() throws NotFoundException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("depName");
        departmentDto.setLocation("depLoc");
        departmentDto.setId(1L);
        DepartmentDto found = departmentService.findDepartmentById(1L);
        assertThat(found).isEqualTo(departmentDto);
    }


    @Test
    void createDepartment() {
        DepartmentDto depDtoToCreate = new DepartmentDto();
        depDtoToCreate.setName("depName");
        depDtoToCreate.setLocation("depLoc");
        DepartmentDto result = new DepartmentDto(1L, depDtoToCreate.getName(), depDtoToCreate.getLocation(), depDtoToCreate.getEmployeesDto());
        Department department = departmentsMapper.toDepartment(depDtoToCreate);
        Mockito.when(departmentRepository.save(department)).thenReturn(new Department(1L, department.getName(), department.getLocation(), null, false));
        departmentService.createDepartment(depDtoToCreate);
        assertThat(depDtoToCreate).isEqualTo(result);
    }

    @Test
    void updateDepartment() throws NotFoundException {
        DepartmentDto toUpdate = new DepartmentDto();
        toUpdate.setName("newName");
        toUpdate.setLocation("newLoc");
        Department department = departmentsMapper.toDepartment(toUpdate);
        Mockito.when(departmentRepository.save(department)).thenReturn(new Department(1L, department.getName(), department.getLocation(), null, false));
        DepartmentDto result = departmentService.updateDepartment(1L, toUpdate);
        assertThat(result).isEqualTo(toUpdate);
    }

    @Test
    void deleteDepartment() throws NotFoundException {
        Department depToDelete = new Department(1L, "depName", "depLoc", null, false);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.of(depToDelete));
        departmentService.deleteDepartment(depToDelete.getId());
        assertThat(true).isEqualTo(depToDelete.isDeleted());
    }
}