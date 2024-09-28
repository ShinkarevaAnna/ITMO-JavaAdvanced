package com.example.car.model.dto.request;

import com.example.car.model.enums.CarMake;
import com.example.car.model.enums.Color;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarInfoRequest {
    @NotEmpty
    String model;
    Color color;
    Integer year;
    BigDecimal price;
    Boolean isNew;
    @NotNull
    CarMake carMake;


}
