package com.example.crud.DAO;

import com.example.crud.entity.Student;
import com.example.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDaoImp implements StudentDao{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id);
        studentRepository.delete(student);
    }


    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
