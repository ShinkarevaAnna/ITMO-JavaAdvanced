package com.example.car.service;

import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    public CarInfoResponse createCar(CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }

        return CarInfoResponse.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .color(request.getColor())
                .model(request.getModel())
                .carMake(request.getCarMake())
                .id(1L)
                .build();
    }

    public CarInfoResponse getCar(Long id) {
        return null;
    }

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            return null;
        }

        return CarInfoResponse.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .color(request.getColor())
                .model(request.getModel())
                .carMake(request.getCarMake())
                .id(1L)
                .build();
    }

    public void deleteCar(Long id) {

    }

    public List<CarInfoResponse> getAllCars() {
        return Collections.emptyList();
    }
}