package com.example.DroolApplication;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="person", schema="public")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int age;
    private boolean eligibleForCar;
    public boolean isEligibleForCar() {
        return eligibleForCar;
    }

    public void setEligibleForCar(boolean eligibleForCar) {
        this.eligibleForCar = eligibleForCar;
    }
}

