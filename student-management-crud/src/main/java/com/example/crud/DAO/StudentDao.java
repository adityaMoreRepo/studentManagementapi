package com.example.crud.DAO;

import com.example.crud.entity.Course;
import com.example.crud.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentDao {
    Student saveStudent(Student student);
    List<Student> getStudents();
    Student findById(int id);
    void deleteStudent(int id);
    Page<Student> findAll(Pageable pageable);
//    ResponseEntity<Student> updateCourse(int id, Course course);
}
