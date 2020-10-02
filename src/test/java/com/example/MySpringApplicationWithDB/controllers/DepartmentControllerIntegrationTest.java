package com.example.MySpringApplicationWithDB.controllers;

import com.example.MySpringApplicationWithDB.api.controllers.DepartmentsController;
import com.example.MySpringApplicationWithDB.dto.DepartmentDto;
import com.example.MySpringApplicationWithDB.services.DepartmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentsController.class)
public class DepartmentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void getDepartments() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(1L, "name", "loc", null);
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto);
        Mockito.when(departmentService.findAllDepartments()).thenReturn(departmentDtoList);

        mvc.perform(get("/departments"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(1L, "name", "loc", null);
        Mockito.when(departmentService.findDepartmentById(1L)).thenReturn(departmentDto);
        mvc.perform(get("/department/{id}", 1L))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.location").value("loc"));
    }

    @Test
    void createDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(null, "name", "loc", null);
        DepartmentDto expectedDto = new DepartmentDto(1L, "name", "loc", null);
        Mockito.when(departmentService.createDepartment(departmentDto)).thenReturn(expectedDto);

        GsonBuilder builder = new GsonBuilder().serializeNulls();
        Gson gson = builder.create();
        String requestJson = gson.toJson(departmentDto);
        String expectedJson = gson.toJson(expectedDto);

        MvcResult mvcResult = mvc.perform(post("/department").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo(expectedJson);
    }

    @Test
    void updateDepartment() throws Exception {
        DepartmentDto newDto = new DepartmentDto(null, "newLame", "newLoc", null);
        DepartmentDto expectedDto = new DepartmentDto(1L, "newLame", "newLoc", null);
        Mockito.when(departmentService.updateDepartment(1L, newDto)).thenReturn(expectedDto);

        GsonBuilder builder = new GsonBuilder().serializeNulls();
        Gson gson = builder.create();
        String requestJson = gson.toJson(newDto);
        String expectedJson = gson.toJson(expectedDto);

        MvcResult mvcResult = mvc.perform(put("/department/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo(expectedJson);
    }

}
