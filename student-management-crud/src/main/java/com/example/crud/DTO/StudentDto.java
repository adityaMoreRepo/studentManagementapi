package com.example.crud.DTO;


import com.example.crud.entity.Address;
import com.example.crud.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private int studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private List<Address> addresses;
    private Course course;
}
