package com.octl3.api.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    String role;

    String description;
}
