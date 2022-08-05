package com.example.crud.controller;

import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Student;
import com.example.crud.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static sun.plugin2.util.PojoUtil.toJson;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    private MockMvc mockMvc;
    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .build();
    }

    @Test
    void getStudentByIdTest() throws Exception {
        //given
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1);
        given(studentService.getStudent(1))
                .willReturn(studentDto);

        //when then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/student/byId/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Matchers.is(1)));
    }

    @Test
    void addStudentTest() throws Exception {
        //given
        Student student = Student.builder()
                .firstName("Adi")
                .build();

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Adi");

        given(studentService.createStudent(student)).willReturn(studentDto);
        //when then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/student/addStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(student))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Adi")));
    }

    @Test
    void getAllStudentsTest() throws Exception {
        //given
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setStudentId(1);
        studentDto1.setFirstName("Adi");
        StudentDto studentDto2 = new StudentDto();
        studentDto2.setStudentId(2);
        studentDto2.setFirstName("Vicky");
        List<StudentDto> studentDtos = new ArrayList<>(Arrays.asList(studentDto1, studentDto2));
        given(studentService.getStudents()).willReturn(studentDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/AllStudents")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[\n" +
                        "    {\n" +
                        "        \"studentId\": 1,\n" +
                        "        \"firstName\": \"Adi\",\n" +
                        "        \"lastName\": null,\n" +
                        "        \"gender\": null,\n" +
                        "        \"addresses\": null,\n" +
                        "        \"course\": null\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"studentId\": 2,\n" +
                        "        \"firstName\": \"Vicky\",\n" +
                        "        \"lastName\": null,\n" +
                        "        \"gender\": null,\n" +
                        "        \"addresses\": null,\n" +
                        "        \"course\": null\n" +
                        "    }\n" +
                        "]"));


    }

    @Test
    @Disabled
    void AllStudentsByPageTest() throws Exception {
        //given
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setStudentId(1);
        studentDto1.setFirstName("Adi");
        StudentDto studentDto2 = new StudentDto();
        studentDto2.setStudentId(2);
        studentDto2.setFirstName("Vicky");
        List<StudentDto> studentDtos = new ArrayList<>(Arrays.asList(studentDto1, studentDto2));
        Page<StudentDto> page = new PageImpl<>(studentDtos);
        Pageable pageable = PageRequest.of(0, 2);
        given(studentService.getAllStudentsByPage(pageable)).willReturn(page);

        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/AllStudentsByPage")
                        .accept(MediaType.APPLICATION_JSON)
                        .requestAttr("pageable", pageable))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(2));

    }

    @Test
    void AllStudentsByPageAndSortTest() throws Exception {
        //given
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setStudentId(1);
        studentDto1.setFirstName("Adi");
        StudentDto studentDto2 = new StudentDto();
        studentDto2.setStudentId(2);
        studentDto2.setFirstName("Vicky");
        List<StudentDto> studentDtos = new ArrayList<>(Arrays.asList(studentDto1, studentDto2));
        Page<StudentDto> page = new PageImpl<>(studentDtos);
        Pageable pageable = PageRequest.of(0, 4, Sort.Direction.ASC, "StudentId");
        given(studentService.getAllStudentsByPage(pageable)).willReturn(page);

        //when then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/AllStudentsByPagingAndSorting")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(2));

    }

}