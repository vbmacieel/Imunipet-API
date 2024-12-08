package com.project.imunipet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.imunipet.dto.vaccine.RequestVaccineDto;
import com.project.imunipet.dto.vaccine.ResponseVaccineDto;
import com.project.imunipet.entity.Pet;
import com.project.imunipet.entity.Vaccine;
import com.project.imunipet.exception.ResourceNotFoundException;
import com.project.imunipet.repository.PetRepository;
import com.project.imunipet.repository.VaccineRepository;

@Service
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final PetRepository petRepository;
    private ModelMapper modelMapper;

    public VaccineService(VaccineRepository vaccineRepository, PetRepository petRepository) {
        this.vaccineRepository = vaccineRepository;
        this.petRepository = petRepository;
        this.modelMapper = new ModelMapper();
    }

    public ResponseVaccineDto saveNewVaccine(Long petId, RequestVaccineDto dto) {
        Vaccine vaccine = modelMapper.map(dto, Vaccine.class);
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException(petId));
        vaccine.setPet(pet);
        vaccineRepository.save(vaccine);
        return modelMapper.map(vaccine, ResponseVaccineDto.class);
    }

    public List<ResponseVaccineDto> findAllVaccinesToPetById(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException(petId));
        return pet.getVaccines().stream().map(vaccine -> modelMapper.map(vaccine, ResponseVaccineDto.class))
                .collect(Collectors.toList());
    }

    public ResponseVaccineDto findVaccineById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return modelMapper.map(vaccine, ResponseVaccineDto.class);
    }
}
