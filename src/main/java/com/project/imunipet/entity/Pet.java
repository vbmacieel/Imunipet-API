package com.project.imunipet.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;

    public Pet() {
    }

    public Pet(String name, LocalDate birthDate, Integer age) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;   
    }
}
