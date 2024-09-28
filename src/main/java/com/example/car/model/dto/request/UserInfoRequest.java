package com.example.car.model.dto.request;


import com.example.car.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoRequest {
    @NotEmpty
    String email;
    String password;
    String firstName;
    String lastName;
    String middleName;
    Integer age;
    Gender gender;
}
