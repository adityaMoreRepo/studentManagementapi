package com.example.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subject {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int subjectId;
    private String subjectName;
    private int totalMarks;
    private int marksObtained;
    private int passingMarks;
    }
