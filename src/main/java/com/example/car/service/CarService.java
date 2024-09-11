package com.example.car.service;

import com.example.car.model.db.entity.Car;
import com.example.car.model.db.repository.CarRepository;
import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import com.example.car.model.enums.CarStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final ObjectMapper mapper;
    private final CarRepository carRepository;

    public CarInfoResponse createCar(CarInfoRequest request) {
        Car car = mapper.convertValue(request, Car.class);
        car.setCreatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.CREATED);
        Car save = carRepository.save(car);
        return mapper.convertValue(save, CarInfoResponse.class);

    }

    public CarInfoResponse getCar(Long id) {
        Car car = getCarFromDB(id);
        return mapper.convertValue(car, CarInfoResponse.class);
    }

    private Car getCarFromDB(long id) {
        return CarRepository.findById(id).orElse(new Car());
    }

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        Car car = getCarFromDB(id);

        car.setColor((request.getColor() == null) ? car.getColor() : request.getColor());
        car.setModel(request.getModel() == null ? car.getModel() : request.getModel());
        car.setCarMake(request.getCarMake() == null ? car.getCarMake() : request.getCarMake());
        car.setYear(request.getYear() == null ? car.getYear() : request.getYear());
        car.setPrice(request.getPrice() == null ? car.getPrice() : request.getPrice());
        car.setIsNew(request.getIsNew() == null ? car.getIsNew() : request.getIsNew());
        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.UPDATED);

        Car save = carRepository.save(car);

        return mapper.convertValue(save, CarInfoResponse.class);

    }

    public void deleteCar(Long id) {
        Car car = getCarFromDB(id);
        car.setUpdatedAt(LocalDateTime.now());
        car.setStatus(CarStatus.DELETED);
        carRepository.save(car);
    }

    public List<CarInfoResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());
    }
}