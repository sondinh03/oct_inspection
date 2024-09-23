package com.octl3.api.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_employee")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @Column(name = "employee_id")
    Long employee_id;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    String gender;

    @Column(name = "date_of_birth")
    LocalDate date_of_birth;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phone_number;

    @Column(name = "email")
    String email;

    @Column(name = "team")
    String team;

    @Column(name = "image")
    String image;

    @Column(name = "identification_number")
    String identification_number;

    @Column(name = "issue_date")
    LocalDate issue_date;

    @Column(name = "place_of_issue")
    String place_of_issue;

    @Column(name = "status")
    String status;
}
