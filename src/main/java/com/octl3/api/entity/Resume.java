package com.octl3.api.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_resume")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Resume {
    @Id
    @Column(name = "resume_id")
    Long resumeId;

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "education")
    String education;

    @Column(name = "experience")
    String experience;

    @Column(name = "skill")
    String skill;

    @Column(name = "achievement")
    String achievement;

    @Column(name = "objective")
    String objective;
}
