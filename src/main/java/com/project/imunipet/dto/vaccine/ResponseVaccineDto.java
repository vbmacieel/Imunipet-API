package com.project.imunipet.dto.vaccine;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponseVaccineDto {
    
    private Long id;
    private String name;
    private LocalDate applicationDate;
    private LocalDate newApplicationDate;
    private String doctorName;
}
