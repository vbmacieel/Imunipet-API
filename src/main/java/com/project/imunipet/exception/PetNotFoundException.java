package com.project.imunipet.exception;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(Long id) {
        super("Could not found pet: " + id);
    }
}
