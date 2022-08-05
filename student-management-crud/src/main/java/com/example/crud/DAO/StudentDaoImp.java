package com.example.crud.DAO;

import com.example.crud.entity.Student;
import com.example.crud.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class StudentDaoImp implements StudentDao {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        log.info("Inside StudentDaoImp method saveStudent");
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        log.info("Inside StudentDaoImp method getStudents");
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        log.info("Inside StudentDaoImp method findById");
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(int id) {
        log.info("Inside StudentDaoImp method deleteStudent");
        Student student = studentRepository.findById(id);
        studentRepository.delete(student);
    }


    public Page<Student> findAll(Pageable pageable) {
        log.info("Inside StudentDaoImp method findAll");
        return studentRepository.findAll(pageable);
    }


    @Override
    public List<Student> findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }
}
