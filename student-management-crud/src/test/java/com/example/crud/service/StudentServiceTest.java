package com.example.crud.service;

import com.example.crud.DAO.StudentDao;
import com.example.crud.DAO.StudentDaoImp;
import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Student;
import com.example.crud.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDao studentDao;
    private StudentService studentService;

    @BeforeEach
    void setUp(){
        studentService = new StudentService(studentDao);
    }

    @Test
    void createStudent() {
        //given
        Student student = Student.builder().build();
        //when
        studentService.createStudent(student);
        //then
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentDao).saveStudent(argumentCaptor.capture());
        Student capturedStudent = argumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void getStudents() {
        //when
        studentService.getStudents();
        //then
        verify(studentDao).getStudents();
    }

    @Test
    @Disabled
    void updateStudent() {
        //given
        Student student = Student.builder()
                .studentId(1)
                .build();
        given(studentDao.findById(1))
                .willReturn(student);
        int id = 2;

        //when
        //then
    assertThatThrownBy(() -> studentService.updateStudent(student, id))
            .isInstanceOf(StudentNotFoundException.class)
            .hasMessageContaining("The Student id " + id + " not found");
    }

    @Test
    void checkUpdatedStudent(){
        //given
        Student student = Student.builder()
                .studentId(1)
                .build();
        given(studentDao.findById(1))
                .willReturn(student);
        //when
        StudentDto studentDto = studentService.updateStudent(student, student.getStudentId());
        //then
        assertThat(studentDto.getStudentId()).isEqualTo(student.getStudentId());
    }

    @Test
    void removeStudent() {
        //when
        studentService.removeStudent(1);
        //then
        verify(studentDao).deleteStudent(1);
    }

    @Test
    void convertEntityToDto() {
        //given
        Student student = Student.builder()
                .studentId(1)
                .firstName("Aditya")
                .gender("Male")
                .lastName("More")
                .build();
        //when
        StudentDto studentDto = studentService.convertEntityToDto(student);
        assertThat(student.getFirstName()).isEqualTo(studentDto.getFirstName());
    }

    @Test
    void getStudent() {
        //given
        //given
        Student student = Student.builder()
                .studentId(1)
                .firstName("Aditya")
                .gender("Male")
                .lastName("More")
                .build();
        given(studentDao.findById(1))
                .willReturn(student);
        //when
        studentService.getStudent(1);
        //then
        verify(studentDao).findById(1);
    }

    @Test
    @Disabled
    void getAllStudentsByPage() {
        //given
        Student student = Student.builder()
                .studentId(1)
                .firstName("Aditya")
                .gender("Male")
                .lastName("More")
                .build();
        Student student1 = Student.builder()
                .studentId(2)
                .firstName("Aditya")
                .gender("Male")
                .lastName("More")
                .build();
        List<Student> list = new ArrayList<>(Arrays.asList(student, student1));
        Page<Student> students = new PageImpl<>(list);
        given(studentDao.findAll(Pageable.ofSize(2))).willReturn(students);
        //when
       Page<StudentDto> studentDtos = studentService.getAllStudentsByPage(Pageable.ofSize(2));
       //then
        assertThat(studentDtos.getContent().get(0).getStudentId()).isEqualTo(1);

    }

}