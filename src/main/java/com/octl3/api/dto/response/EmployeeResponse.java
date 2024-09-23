package com.octl3.api.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    Long employee_id;
    String code;
    String name;
    String gender;
    LocalDate date_of_birth;
    String address;
    String phone_number;
    String email;
    String team;
    String image;
    String identification_number;
    LocalDate issue_date;
    String place_of_issue;
    String status;
}
