package com.example.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int studentId;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    private String gender;

    @OneToMany(targetEntity = Address.class,
            cascade = CascadeType.ALL
            //optional = false
            )
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private List<Address> addresses;


    @ManyToOne(targetEntity = Course.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER // this will include this attribute while fetching
            // the parent table here Student entity
            // while lazy won't fetch it
            // by default it is optional relationship and not mandatory
            // that is for studentClass student is optional if it's bidirectional
            // or for Student entity we don't need studentClass mandatory if as follows
            //optional = false
    )
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;
}
