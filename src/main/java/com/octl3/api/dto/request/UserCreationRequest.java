package com.octl3.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    String email;
    String username;
    String firstName;
    String lastName;
    String password;
    String role;
    String duty;
}
