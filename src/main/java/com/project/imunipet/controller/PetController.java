package com.project.imunipet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.project.imunipet.dto.pet.RequestPetDto;
import com.project.imunipet.dto.pet.ResponsePetDto;
import com.project.imunipet.service.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResponsePetDto> findAllPets() {
        return service.findAllPets();
    }

    @PostMapping
    public ResponsePetDto savePet(@RequestBody RequestPetDto dto) {
        return service.savePet(dto);
    }

    @GetMapping("{id}")
    public ResponsePetDto findPetById(@PathVariable Long id) {
        return service.findPetById(id);
    }

    @PutMapping("{id}")
    public ResponsePetDto updatePet(@RequestBody RequestPetDto dto,
            @PathVariable Long id) {
        return service.updatePet(dto, id);
    }

    @DeleteMapping("{id}")
    public void deletePet(@PathVariable Long id) {
        service.deletePet(id);
    }
}
