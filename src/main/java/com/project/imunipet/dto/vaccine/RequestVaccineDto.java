package com.project.imunipet.dto.vaccine;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestVaccineDto {
    
    @NotNull
    @Size(min = 5, max = 50)
    private String name;
    @NotNull
    private LocalDate applicationDate;
    @NotNull
    private LocalDate newApplicationDate;
    private String doctorName;
}
