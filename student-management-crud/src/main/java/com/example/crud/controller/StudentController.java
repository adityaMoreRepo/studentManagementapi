package com.example.crud.controller;

import com.example.crud.DAO.StudentDao;
import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Address;
import com.example.crud.entity.Course;
import com.example.crud.entity.Student;
import com.example.crud.repository.StudentRepository;
import com.example.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/addStudent")
    public StudentDto addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/byId/{id}")
    public StudentDto getStudentById(@PathVariable int id){
        return studentService.getStudent(id);
    }
    @GetMapping("/AllStudents")
    public List<StudentDto> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/AllStudentsByPage")
    public Page<StudentDto> getAllStudentsByPage(Pageable pageable){
        return studentService.getAllStudentsByPage(pageable);
    }

    @DeleteMapping("/removeStudent/{id}")
    public ResponseEntity<StudentDto> removeStudent(@PathVariable int id){
        return studentService.removeStudent(id);
    }

    @PutMapping("/updateStudent/{id}")
    public StudentDto updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudent(student, id);
    }

    @PatchMapping("/addCourse/{id}")
    public StudentDto addCourseToStudent(@PathVariable int id, @RequestBody Course course){
        return studentService.addCourseToStudent(id, course);
    }

}
