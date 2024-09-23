package com.octl3.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipRequest {
    String name;
    String gender;
    LocalDate date_of_birth;
    String address;
    String phone_number;
    String relationship;
    String profession;
}
