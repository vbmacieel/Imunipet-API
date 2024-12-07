package com.project.imunipet.dto.pet;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RequestPetDto {

    private String name;
    private LocalDate birthDate;
    private Integer age;
}
