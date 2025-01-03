package com.project.imunipet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.imunipet.entity.vaccine.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

}
