package com.example.agrokushproject.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String lastName;
    String firstName;
    String username;
    String email;
    String password;
}
