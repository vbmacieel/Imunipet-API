package com.project.imunipet.entity.pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.project.imunipet.entity.vaccine.Vaccine;
import com.project.imunipet.entity.user.Client;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String race;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonManagedReference
    private Client client;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Vaccine> vaccines = new ArrayList<>();

    public Pet(String name, LocalDate birthDate, String race, Integer age) {
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.age = age;
    }
}
