package com.example.crud.controller;

import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Student;
import com.example.crud.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/addStudent")
    public StudentDto addStudent(@RequestBody Student student) {
        log.info("Inside StudentController method addStudent");
        return studentService.createStudent(student);
    }

//    @GetMapping(value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/byId/{id}")
    public StudentDto getStudentById(@PathVariable int id){
        log.info("Inside StudentController method getStudentById");
        return studentService.getStudent(id);
    }

    @GetMapping("/byFirstName")
    public List<StudentDto> getStudentsByFirstName(@RequestParam String firstName){
        log.info("Inside StudentController method getStudentByFirstName");
        return studentService.getStudentsByFirstName(firstName);
    }

    @GetMapping("/AllStudents")
    public List<StudentDto> getAllStudents() {
        log.info("Inside StudentController method getAllStudents");
        return studentService.getStudents();
    }

    @GetMapping("/AllStudentsByPage")
    public Page<StudentDto> getAllStudentsByPage(Pageable pageable){
        log.info("Inside StudentController method getAllStudentsByPage");
        return studentService.getAllStudentsByPage(pageable);
    }

    @GetMapping("/AllStudentsByPagingAndSorting")
    public Page<StudentDto> getAllStudentsByPagingAndSorting(@RequestParam Optional<Integer> page,
                                                   @RequestParam Optional<String> sortBy){
        log.info("Inside StudentController method getAllStudentsByPagingAndSorting");
        return studentService.getAllStudentsByPage(PageRequest.of(page.orElse(0), 4, Sort.Direction.ASC, sortBy.orElse("StudentId")));
    }

    @DeleteMapping("/removeStudent/{id}")
    public ResponseEntity<StudentDto> removeStudent(@PathVariable int id){
        log.info("Inside StudentController method removeStudent");
        return studentService.removeStudent(id);
    }

    @PutMapping("/updateStudent/{id}")
    public StudentDto updateStudent(@PathVariable int id, @RequestBody Student student){
        log.info("Inside StudentController method updateStudent");
        return studentService.updateStudent(student, id);
    }

    @PatchMapping("/addCourse/{id}")
    public StudentDto addCourseToStudent(@PathVariable int id, @RequestBody Course course){
        log.info("Inside StudentController method addCourseToStudent");
        return studentService.addCourseToStudent(id, course);
    }

}
