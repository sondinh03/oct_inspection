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
@Table(name = "tbl_termination")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Termination {
    @Id
    @Column(name = "termination_id")
    Long id;

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "validator")
    String validator;

    @Column(name = "termination_date")
    LocalDate terminationDate;

    @Column(name = "termination_reason")
    String terminationReason;

    @Column(name = "comment")
    String comment;

    @Column(name = "type")
    String type;
}
