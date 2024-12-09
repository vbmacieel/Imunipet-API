package com.project.imunipet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.imunipet.dto.vaccine.RequestVaccineDto;
import com.project.imunipet.dto.vaccine.ResponseVaccineDto;
import com.project.imunipet.service.VaccineService;

import jakarta.validation.Valid;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/")
public class VaccineController {

    private final VaccineService service;

    public VaccineController(VaccineService service) {
        this.service = service;
    }

    @GetMapping("pets/{petId}/vaccines")
    public ResponseEntity<List<ResponseVaccineDto>> findAllVaccinesFromPet(@PathVariable Long petId) {
        List<ResponseVaccineDto> responceVaccines = service.findAllVaccinesToPetById(petId);
        return ResponseEntity.ok(responceVaccines);
    }

    @PostMapping("vaccines/{petId}")
    public ResponseEntity<ResponseVaccineDto> saveNewVaccine(@Valid @RequestBody RequestVaccineDto dto,
            @PathVariable Long petId, UriComponentsBuilder uriBuilder) {
        ResponseVaccineDto responseVaccine = service.saveNewVaccine(petId, dto);
        String location = uriBuilder.path("/api/vaccines/{id}").buildAndExpand(responseVaccine.getId()).toUriString();
        return ResponseEntity.created(URI.create(location)).body(responseVaccine);
    }

    @GetMapping("vaccines/{id}")
    public ResponseEntity<ResponseVaccineDto> findVaccineById(@PathVariable Long id) {
        ResponseVaccineDto responseVaccine = service.findVaccineById(id);
        return ResponseEntity.ok(responseVaccine);
    }
}
