package com.example.car.model.db.entity;

import com.example.car.model.enums.Gender;
import com.example.car.model.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name= "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "age")
    Integer age;

    @Column(name = "gender", columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "created_at")
            @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
            @UpdateTimestamp
    LocalDateTime updatedAt;

    @Column(name = "status")
    UserStatus status;

    @OneToMany
    @JsonManagedReference (value = "driver_cars")
    List<Car> cars;
}
