package com.example.crud.service;

import com.example.crud.DAO.StudentDao;
import com.example.crud.DAO.StudentDaoImp;
import com.example.crud.DTO.StudentDto;
import com.example.crud.entity.Course;
import com.example.crud.entity.Student;
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
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public StudentDto createStudent(Student student) {
        studentDao.saveStudent(student);
        return convertEntityToDto(student);

    }

    public List<StudentDto> getStudents() {
        return studentDao.getStudents()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public StudentDto updateStudent(Student student, int id) {
        log.info("Inside updateStudent method of studentService");
        Student student1 = studentDao.findById(id);

        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setGender(student.getGender());
        student1.setAddresses(student.getAddresses());
        student1.setCourse(student.getCourse());
        studentDao.saveStudent(student1);

        return convertEntityToDto(student1);
    }

    public ResponseEntity<StudentDto> removeStudent(int id) {
        studentDao.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    public StudentDto convertEntityToDto(Student student) {
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
        return convertEntityToDto(studentDao.findById(id));
    }

    public Page<StudentDto> getAllStudentsByPage(Pageable pageable) {
        Page<StudentDto> dtoPage = studentDao.findAll(pageable)
                .map((student) -> convertEntityToDto(student));
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
        Student student = studentDao.findById(id);
        student.setCourse(course);
        studentDao.saveStudent(student);
        return convertEntityToDto(student);
    }
}
