package com.project.imunipet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.imunipet.dto.pet.RequestPetDto;
import com.project.imunipet.dto.pet.ResponsePetDto;
import com.project.imunipet.entity.Pet;
import com.project.imunipet.exception.ResourceNotFoundException;
import com.project.imunipet.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository repository;
    private final ModelMapper modelMapper;

    public PetService(PetRepository repository) {
        this.repository = repository;
        modelMapper = new ModelMapper();
    }

    public List<ResponsePetDto> findAllPets() {
        return repository.findAll().stream().map(pet -> modelMapper.map(pet, ResponsePetDto.class))
                .collect(Collectors.toList());
    }

    public ResponsePetDto savePet(RequestPetDto dto) {
        Pet pet = modelMapper.map(dto, Pet.class);
        repository.save(pet);
        return modelMapper.map(pet, ResponsePetDto.class);
    }

    public ResponsePetDto findPetById(Long id) {
        Pet pet = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return modelMapper.map(pet, ResponsePetDto.class);
    }

    public ResponsePetDto updatePet(RequestPetDto dto, Long id) {
        Pet pet = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        modelMapper.map(pet, dto);

        Pet updatePet = repository.save(pet);
        return modelMapper.map(updatePet, ResponsePetDto.class);
    }

    public void deletePet(Long id) {
        Optional<Pet> optional = repository.findById(id);
        if (optional.isEmpty()) throw new ResourceNotFoundException(id);
        repository.deleteById(id);
    }
}
