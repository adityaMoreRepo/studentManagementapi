package com.example.crud.repository;

import com.example.crud.entity.Address;
import com.example.crud.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Testing Persistence/repository layer
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Commit
    @Order(1)
    @Test
    public void saveStudent() {
        Address permAddress = Address.builder()
                .city("Pune")
                .flatNo(201)
                .road("MG Road")
                .addressType("Permanent")
                .mobileNo("9028594021")
                .state("Maharashtra")
                .pin("412041")
                .build();

        Address tempAddress = Address.builder()
                .addressType("Temporary")
                .city("Pune")
                .flatNo(105)
                .road("West Cross Road")
                .mobileNo("9039602061")
                .state("Maharashtra")
                .pin("414041")
                .build();

        Student student = Student.builder()
                .firstName("Vikas")
                .lastName("Sharma")
                .gender("Male")
                .addresses(new ArrayList<>(Arrays.asList(permAddress, tempAddress)))
                .build();
        studentRepository.save(student);

        Student student1 = Student.builder()
                .firstName("Vikas")
                .lastName("Sharma")
                .gender("Male")
                .build();
        studentRepository.save(student1);

        Assertions.assertThat(student.getStudentId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    public void findByIdTest(){
//        Student student = Student.builder()
//                .firstName("Vikas")
//                .lastName("Sharma")
//                .gender("Male")
//                .build();
//        studentRepository.save(student);

        Student studentFromRepo = studentRepository.findById(1);
        Assertions.assertThat(studentFromRepo.getStudentId()).isEqualTo(1);
    }

    @Order(3)
    @Test
    public void findAllTest(){
        List<Student> students = studentRepository.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(1);
    }

    @Order(4)
    @Test
    public void findByFirstNameTest(){
        List<Student> students = studentRepository.findByFirstName("Vikas");
        System.out.println("Students: " + students);
        Assertions.assertThat(students.size()).isGreaterThan(1);
    }

    @Order(5)
    @Test
    public void checkIfStudentExistsId(){
        int id = 1;
        Student student = Student.builder()
                .firstName("Aditya")
                .lastName("More")
                .gender("Male")
                .build();
        studentRepository.save(student);
        Boolean expected = studentRepository.existsById(id);
        Assertions.assertThat(expected).isTrue();
    }
}