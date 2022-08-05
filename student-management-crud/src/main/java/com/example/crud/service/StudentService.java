package com.example.crud.service;

import com.example.crud.DAO.StudentDao;
import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Student;
import com.example.crud.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public StudentDto createStudent(Student student) {
        log.info("Inside StudentService method createStudent");
        Student student1 = studentDao.saveStudent(student);
        return convertEntityToDto(student1);

    }

    public List<StudentDto> getStudents() {
        log.info("Inside StudentService method getStudents");
        return studentDao.getStudents()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public StudentDto updateStudent(Student student, int id) {
        log.info("Inside StudentService method updateStudent");
        Student student1 = studentDao.findById(id);
        if (student1 == null)
            throw new StudentNotFoundException("The Student id " + id + " not found");


        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setGender(student.getGender());
        student1.setAddresses(student.getAddresses());
        student1.setCourse(student.getCourse());
        studentDao.saveStudent(student1);

        return convertEntityToDto(student1);
    }

    public ResponseEntity<StudentDto> removeStudent(int id) {
        log.info("Inside StudentService method removeStudent");
        studentDao.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    public StudentDto convertEntityToDto(Student student) {
        log.info("Inside StudentService method convertEntityToDto");
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setGender(student.getGender());
        studentDto.setLastName(student.getLastName());
        studentDto.setAddresses(student.getAddresses());
        studentDto.setCourse(student.getCourse());
        return studentDto;
    }


    public StudentDto getStudent(int id) {
        log.info("Inside StudentService method getStudent");
        Student student = studentDao.findById(id);
        if (student == null)
            throw new StudentNotFoundException("The Student id " + id + " not found");

        return convertEntityToDto(student);
    }

    public Page<StudentDto> getAllStudentsByPage(Pageable pageable) {
        log.info("Inside StudentService method getAllStudentsByPage");
        Page<StudentDto> dtoPage = studentDao.findAll(pageable)
                .map(this::convertEntityToDto);
        return dtoPage;
    }


    //logic using anonymous inner class
//    Page<ObjectEntity> entities = objectEntityRepository.findAll(pageable);
//    Page<ObjectDto> dtoPage = entities.map(new Converter<ObjectEntity, ObjectDto>() {
//        @Override
//        public ObjectDto convert(ObjectEntity entity) {
//            ObjectDto dto = new ObjectDto();
//            // Conversion logic
//
//            return dto;
//        }
//    });

    public StudentDto addCourseToStudent(int id, Course course) {
        log.info("Inside StudentService method addCourseToStudent");
        Student student = studentDao.findById(id);
        student.setCourse(course);
        studentDao.saveStudent(student);
        return convertEntityToDto(student);
    }

    public List<StudentDto> getStudentsByFirstName(String firstName) {
        log.info("Inside StudentService method getStudentsByFirstName");
        return studentDao.findByFirstName(firstName)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
