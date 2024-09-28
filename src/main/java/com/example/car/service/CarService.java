package com.example.car.service;

import com.example.car.exceptions.CustomException;
import com.example.car.model.db.entity.Car;
import com.example.car.model.db.entity.User;
import com.example.car.model.db.repository.CarRepository;
import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.request.CarToUserRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import com.example.car.model.enums.CarStatus;
import com.example.car.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final UserService userService;
    private final ObjectMapper mapper;
    private final CarRepository carRepository;

    public CarInfoResponse createCar(CarInfoRequest request) {
        Car car = mapper.convertValue(request, Car.class);
        car.setStatus(CarStatus.CREATED);
        return mapper.convertValue(carRepository.save(car), CarInfoResponse.class);

    }

    public CarInfoResponse getCar(Long id) {
        return mapper.convertValue(getCarById(id), CarInfoResponse.class);
    }

    private Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
    }

    public CarInfoResponse updateCar(Long id, CarInfoRequest request) {
        Car car = getCarById(id);

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
        Car car = getCarById(id);
        car.setStatus(CarStatus.DELETED);
        carRepository.save(car);
    }

//    public List<CarInfoResponse> getAllCars() {
//        return carRepository.findAll().stream()
//                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
//                .collect(Collectors.toList());
//    }
public Page<CarInfoResponse> getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {


    Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);

    Page<Car> all;
    if (filter == null) {
        all = carRepository.findAllByStatusNot(pageRequest, CarStatus.DELETED);
    } else {
        all = carRepository.findAllByStatusNotFiltered(pageRequest, CarStatus.DELETED, filter.toUpperCase());
    }

    List<CarInfoResponse> content = all.getContent().stream()
            .map(car -> mapper.convertValue(car, CarInfoResponse.class))
            .collect(Collectors.toList());

    return new PageImpl<>(content, pageRequest, all.getTotalElements());
}

    public void addCarToUser(CarToUserRequest request) {
//        Car car = carRepository.findById(request.getCarId()).orElse(null);
//
//        if (car == null) {
//            return;
//        }
//
//        User userFromDB = userService.getUserFromDB(request.getUserId());
//
//        if (userFromDB == null) {
//            return;
//        }
//
//        userFromDB.getCars().add(car);
//
//        userService.updateUserData(userFromDB);
//
//        car.setUser(userFromDB);
//        carRepository.save(car);
        Car car = getCarById(request.getCarId());
        User userFromDB = userService.getUserFromDB(request.getUserId());
        userFromDB.getCars().add(car);
        userService.updateUserData(userFromDB);
        car.setUser(userFromDB);
        carRepository.save(car);
    }

    public Car getSomeCar() {
        return carRepository.getSomeCar(true);
    }

//    public List<CarInfoResponse> getUserCars(Long id){
//        User user = userService.getUserFromDB(id);
//        if(user == null){
//            return  null;
//        }
//        return user.getCars().stream()
//                .map(car -> mapper.convertValue(car,CarInfoResponse.class))
//                .collect(Collectors.toList());
//    }

    public Page<CarInfoResponse> getUserCars(Long id, Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Car> userCars;
        if (filter == null) {
            userCars = carRepository.findAllCarsByStatusNot(id, pageRequest, CarStatus.DELETED);
        } else {
            userCars = carRepository.findAllCarsByStatusNotFiltered(id, pageRequest, CarStatus.DELETED, filter.toLowerCase());
        }

        List<CarInfoResponse> content = userCars.getContent().stream()
                .map(car -> mapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, userCars.getTotalElements());
    }

}
