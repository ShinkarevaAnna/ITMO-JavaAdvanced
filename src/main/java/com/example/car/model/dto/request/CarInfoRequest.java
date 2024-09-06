package com.example.car.model.dto.request;

import com.example.car.model.enums.CarMake;
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
public class CarInfoRequest {
    @NotEmpty
    String email;
    String password;
    String color;
    String model;
    @NotNull
    CarMake carMake;
}
