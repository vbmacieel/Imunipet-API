package com.project.imunipet.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate applicationDate;
    private LocalDate newApplicationDate;
    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "petId")
    @JsonBackReference
    private Pet pet;
    
    public Vaccine(String name, LocalDate applicationDate, LocalDate newApplicationDate, String doctorName) {
        this.name = name;
        this.applicationDate = applicationDate;
        this.newApplicationDate = newApplicationDate;
        this.doctorName = doctorName;
    }
}
