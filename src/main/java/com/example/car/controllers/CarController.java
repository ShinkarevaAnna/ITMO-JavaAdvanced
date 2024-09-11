package com.example.car.controllers;

import com.example.car.model.dto.request.CarInfoRequest;
import com.example.car.model.dto.response.CarInfoResponse;
import com.example.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public List<CarInfoResponse> getAllCars() {
        return carService.getAllCars();
    }

}
