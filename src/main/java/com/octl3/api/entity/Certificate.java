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
@Table(name = "tbl_certificate_diplomas")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certificate {
    @Id
    @Column(name = "cer_dip_id")
    Long id;

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    String type;

    @Column(name = "field")
    LocalDate field;

    @Column(name = "issue_date")
    LocalDate issueDate;

    @Column(name = "place_of_issue")
    String placeOfIssue;

    @Column(name = "description")
    String description;

    @Column(name = "expiration_date")
    LocalDate expirationDate;

    @Column(name = "rating")
    String rating;
}
