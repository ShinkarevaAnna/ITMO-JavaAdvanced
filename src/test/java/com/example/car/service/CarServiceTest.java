package com.example.car.service;

import com.example.car.model.db.entity.Car;
import com.example.car.model.db.entity.User;
import com.example.car.model.db.repository.CarRepository;
import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.request.CarToUserRequest;
import com.example.car.model.dto.request.UserInfoRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import com.example.car.model.dto.response.UserInfoResponse;
import com.example.car.model.enums.CarMake;
import com.example.car.model.enums.CarStatus;
import com.example.car.model.enums.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @InjectMocks
    private CarService carService;
    @Mock
    private UserService userService;
    @Mock
    private CarRepository carRepository;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createCar() {
        CarInfoRequest request = new CarInfoRequest();
        request.setModel("240");

        Car car = new Car();
        car.setId(1L);

        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarInfoResponse result = carService.createCar(request);

        assertEquals(car.getId(), result.getId());
    }

    @Test
    public void getCar() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("240");
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        CarInfoResponse result = carService.getCar(1L);
        CarInfoResponse expected = mapper.convertValue(car, CarInfoResponse.class);
        assertEquals(expected, result);
    }

    @Test
    public void updateCar() {
        Long carId = 1L;
        CarInfoRequest request = new CarInfoRequest();
        request.setModel("240");
        request.setCarMake(CarMake.VOLVO);
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        carService.updateCar(car.getId(), request);
        verify(carRepository, times(1)).save(any(Car.class));
        assertEquals(CarStatus.UPDATED, car.getStatus());
    }

    @Test
    public void deleteCar() {
        Car car = new Car();
        car.setId(1L);

        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        carService.deleteCar(car.getId());

        verify(carRepository, times(1)).save(any(Car.class));
        assertEquals(CarStatus.DELETED, car.getStatus());
    }

    @Test
    public void getAllCars() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Car car = new Car();
        car.setId(1L);
        car.setModel("940");
        car.setCarMake(CarMake.VOLVO);
        Car car1 = new Car();
        car1.setId(2L);
       car1.setCarMake(CarMake.PORSCHE);
       car1.setModel("911");
        List<Car> cars = List.of(car, car1);
        Page<Car> carPage = new PageImpl<>(cars, pageRequest, cars.size());
        when(carRepository.findAllByStatusNot(pageRequest, CarStatus.DELETED)).thenReturn(carPage);
        Page<CarInfoResponse> result = carService.getAllCars(0, 10, "id", Sort.Direction.ASC, null);
        assertEquals(2, result.getTotalElements());
        assertEquals(car.getId(), result.getContent().get(0).getId());
        assertEquals("940", result.getContent().get(0).getModel());
        assertEquals(CarMake.VOLVO, result.getContent().get(0).getCarMake());
        assertEquals(car1.getId(), result.getContent().get(1).getId());
        assertEquals("911", result.getContent().get(1).getModel());
        assertEquals(CarMake.PORSCHE, result.getContent().get(1).getCarMake());
    }

    @Test
    public void addCarToUser() {
        Car car = new Car();
        car.setId(1L);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        User user = new User();
        user.setId(1L);
        user.setCars(new ArrayList<>());
        when(userService.getUserFromDB(user.getId())).thenReturn(user);
        when(userService.updateUserData(any(User.class))).thenReturn(user);
        CarToUserRequest request = CarToUserRequest.builder()
                .carId(car.getId())
                .userId(user.getId())
                .build();
        carService.addCarToUser(request);
        verify(carRepository, times(1)).save(any(Car.class));
        assertEquals(user.getId(), car.getUser().getId());
        assertTrue(user.getCars().contains(car));
    }

    @Test
    public void getUserCars() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Car car = new Car();
        car.setId(1L);
        car.setModel("940");
        car.setCarMake(CarMake.VOLVO);
        Car car1 = new Car();
        car1.setId(2L);
        car1.setCarMake(CarMake.PORSCHE);
        car1.setModel("911");
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        User user = new User();
        user.setId(1L);
        List<Car> cars = List.of(car, car1);
        Page<Car> carPage = new PageImpl<>(cars, pageRequest, cars.size());
        when(carRepository.findAllCarsByStatusNot(user.getId(),pageRequest, CarStatus.DELETED)).thenReturn(carPage);
        user.setCars(cars);
        Page<CarInfoResponse> result = carService.getUserCars(user.getId(),0, 10, "id", Sort.Direction.ASC, null);
        assertEquals(2, result.getTotalElements());
        assertEquals(car.getId(), result.getContent().get(0).getId());
        assertEquals("940", result.getContent().get(0).getModel());
        assertEquals(CarMake.VOLVO, result.getContent().get(0).getCarMake());
        assertEquals(car1.getId(), result.getContent().get(1).getId());
        assertEquals("911", result.getContent().get(1).getModel());
        assertEquals(CarMake.PORSCHE, result.getContent().get(1).getCarMake());
    }
}