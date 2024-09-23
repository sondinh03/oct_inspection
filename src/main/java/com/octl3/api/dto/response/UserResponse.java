package com.octl3.api.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    Long userId;
    String email;
    String username;
    String firstName;
    String lastName;
    String password;
    String role;
    String duty;
}
