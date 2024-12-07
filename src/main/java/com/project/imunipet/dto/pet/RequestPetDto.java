package com.project.imunipet.dto.pet;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestPetDto {

    @NotNull
    @Size(min = 2, max = 20, message = "The name should be between 2 and 100 characters long")
    private String name;

    @NotNull
    private LocalDate birthDate;
    private String race;
    private Integer age;
}
