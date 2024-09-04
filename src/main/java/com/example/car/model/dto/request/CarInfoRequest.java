package com.example.car.model.dto.request;

import com.example.car.model.enums.CarMake;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoRequest {
    @NonNull
    String email;
    @NonNull
    String password;
    String color;
    String model;
    @NonNull
    CarMake carMake;
}
