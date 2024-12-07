package com.project.imunipet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.imunipet.dto.pet.RequestPetDto;
import com.project.imunipet.dto.pet.ResponsePetDto;
import com.project.imunipet.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponsePetDto>> findAllPets() {
        return ResponseEntity.ok(service.findAllPets());
    }

    @PostMapping
    public ResponseEntity<ResponsePetDto> savePet(@Valid @RequestBody RequestPetDto dto,
            UriComponentsBuilder uriBuilder) {
        ResponsePetDto responsePet = service.savePet(dto);
        String location = uriBuilder.path("/api/pets/{id}").buildAndExpand(responsePet.getId()).toUriString();
        return ResponseEntity.created(URI.create(location)).body(responsePet);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponsePetDto> findPetById(@PathVariable Long id) {
        ResponsePetDto responsePet = service.findPetById(id);
        return ResponseEntity.ok(responsePet);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponsePetDto> updatePet(@Valid @RequestBody RequestPetDto dto,
            @PathVariable Long id) {
        ResponsePetDto updatePet = service.updatePet(dto, id);
        return ResponseEntity.ok(updatePet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        service.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
