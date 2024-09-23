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
import java.util.Date;

@Entity
@Table(name = "tbl_registration_form")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationForm {
    @Id
    @Column(name = "reg_form_id")
    Long registrationFormId;

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "creator")
    LocalDate creator;

    @Column(name = "creation_date")
    LocalDate creationDate;

    @Column(name = "content")
    String content;

    @Column(name = "position")
    String position;

    @Column(name = "department")
    String department;

    @Column(name = "submit_date")
    Date submitDate;

    @Column(name = "accept_date")
    Date acceptDate;

    @Column(name = "status")
    String status;

    @Column(name = "rejection_date")
    Date rejectionDate;

    @Column(name = "rejection_reason")
    String rejectionReason;
}
