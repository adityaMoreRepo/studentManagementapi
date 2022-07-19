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
@Table(
        name = "address",
        uniqueConstraints = @UniqueConstraint(name = "unique_mobile_no",
        columnNames = "mobileNo")
)
@Builder
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int addressId;

    private String addressType;

    private int flatNo;

    private String road;

    private String city;

    private String state;

    @Column(
            nullable = false
    )
    private String pin;

    private String mobileNo;
}
