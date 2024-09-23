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
public class FamilyRelationshipResponse {
    Long id;
    Long employeeId;
    String name;
    String gender;
    LocalDate date_of_birth;
    String address;
    String phone_number;
    String relationship;
    String profession;
}
