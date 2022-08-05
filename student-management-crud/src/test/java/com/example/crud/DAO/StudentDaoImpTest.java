package com.example.crud.DAO;

import com.example.crud.entity.Student;
import com.example.crud.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentDaoImpTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentDao dao;

    @BeforeEach
    void setUp(){
        dao = new StudentDaoImp(studentRepository);
    }

    @Test
    public void canGetAllStudents(){
        //when
        dao.getStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void saveStudent() {
        //given
        Student student = Student.builder().build();
        //when
        dao.saveStudent(student);
        //then
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(argumentCaptor.capture());
        Student capturedStudent = argumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void getStudents() {
        //when
        List<Student> students = dao.getStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void findById() {
        //when
        Student student = dao.findById(1);
        //then
        verify(studentRepository).findById(1);
    }

    @Test
    void deleteStudent() {
        dao.deleteStudent(1);
        verify(studentRepository).delete(studentRepository.findById(1));
    }

    @Test
    void findAll() {
        //when
        dao.findAll(Pageable.ofSize(1));
        //then
        verify(studentRepository).findAll(Pageable.ofSize(1));
    }

    @Test
    void findByFirstName() {
        //when
        dao.findByFirstName("Aditya");
        //then
        verify(studentRepository).findByFirstName("Aditya");
    }
}