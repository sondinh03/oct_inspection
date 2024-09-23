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
@Table(name = "tbl_family_relationship")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FamilyRelationship {
    @Id
    @Column(name = "family_rlt_id")
    Long id;

    @Column(name = "employee_id")
    Long employeeId;

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

    @Column(name = "relationship")
    String relationship;

    @Column(name = "profession")
    String profession;
}
