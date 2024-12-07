package com.project.imunipet.dto.pet;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponsePetDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer age;
}