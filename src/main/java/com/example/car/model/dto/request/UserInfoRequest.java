package com.example.car.model.dto.request;


import com.example.car.model.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoRequest {
    @NonNull
    String email;
    @NonNull
    String password;
    String firstName;
    String lastName;
    String middleName;
    Integer age;
    @NonNull
    Gender gender;
}
