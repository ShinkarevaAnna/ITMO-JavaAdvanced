package com.example.car.model.db.entity;

import com.example.car.model.enums.CarMake;
import com.example.car.model.enums.CarStatus;
import com.example.car.model.enums.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_at")
            @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
            @UpdateTimestamp
    LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    CarStatus status;

    @Column(name = "brand")
    CarMake carMake;

    @Column(name = "model")
    String model;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    Color color;

    @Column(name = "year")
    Integer year;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "is_new")
    Boolean isNew;

    @ManyToOne
    User user;
}
