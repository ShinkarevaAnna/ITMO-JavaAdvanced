package com.example.car.controllers;

import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.request.CarToUserRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import com.example.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.car.constants.Constants.CARS_API;

@Tag(name = "Cars")
@RestController
@RequestMapping(CARS_API)
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    @Operation(summary = "Create car")
    public CarInfoResponse createCar(@RequestBody CarInfoRequest request) {
        return carService.createCar(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by id")
    public CarInfoResponse getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update car by id")
    public CarInfoResponse updateCar(@PathVariable Long id, @RequestBody CarInfoRequest request) {
        return carService.updateCar(id, request);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete car by id")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "418", description = "boiling"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),

    })
    @Operation(summary = "Get all cars")
    public Page<CarInfoResponse> getAllCars(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer perPage,
                                            @RequestParam(defaultValue = "carMake") String sort,
                                            @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                            @RequestParam(required = false) String filter

    ) {
        return carService.getAllCars(page, perPage, sort, order, filter);
    }

    @PostMapping("/carToUser")
    @Operation(summary = "Add car to user")
    public void addCarToUser(@RequestBody @Valid CarToUserRequest request) {
        carService.addCarToUser(request);
    }

    @GetMapping("/userCars/{id}")
    @Operation(summary = "get user cars")
    public Page<CarInfoResponse> getUserCars(@PathVariable Long id,
                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer perPage,
                                                        @RequestParam(defaultValue = "carMake") String sort,
                                                        @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                                        @RequestParam(required = false) String filter

    ) {
        return carService.getUserCars(id, page, perPage, sort, order, filter);
    }

}
