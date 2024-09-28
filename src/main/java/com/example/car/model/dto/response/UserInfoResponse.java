package com.example.car.model.dto.response;

import com.example.car.model.dto.request.UserInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class UserInfoResponse extends UserInfoRequest {
    Long id;
}
