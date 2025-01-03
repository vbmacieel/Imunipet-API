package com.project.imunipet.dto.pet;

import java.time.LocalDate;
import java.util.List;

import com.project.imunipet.entity.vaccine.Vaccine;

import lombok.Data;

@Data
public class ResponsePetDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String race;
    private Integer age;
    private List<Vaccine> vaccines;
}
