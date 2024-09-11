package com.example.car.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum Color {
    WHITE("white"),
    BLACK("black"),
    GREEN("green"),
    RED("red"),
    BLUE("blue");

private final String description;
}
