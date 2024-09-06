package com.example.car.model.dto.response;

import com.example.car.model.dto.request.CarInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoResponse extends CarInfoRequest {
    Long id;
}
