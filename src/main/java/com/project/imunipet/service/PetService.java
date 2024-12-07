package com.project.imunipet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.imunipet.dto.pet.RequestPetDto;
import com.project.imunipet.dto.pet.ResponsePetDto;
import com.project.imunipet.entity.Pet;
import com.project.imunipet.exception.PetNotFoundException;
import com.project.imunipet.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository repository;
    private ModelMapper modelMapper;

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
        Pet pet = repository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
        return modelMapper.map(pet, ResponsePetDto.class);
    }

    public ResponsePetDto updatePet(RequestPetDto dto, Long id) {
        return repository.findById(id).map(pet -> {
            pet.setName(dto.getName());
            pet.setBirthDate(dto.getBirthDate());
            pet.setAge(dto.getAge());

            repository.save(pet);

            return modelMapper.map(pet, ResponsePetDto.class);
        }).orElseGet(() -> {
            Pet newPet = modelMapper.map(dto, Pet.class);
            repository.save(newPet);

            return modelMapper.map(newPet, ResponsePetDto.class);
        });
    }

    public void deletePet(Long id) {
        repository.deleteById(id);
    }
}
