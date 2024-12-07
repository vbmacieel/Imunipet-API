package com.project.imunipet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.imunipet.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
