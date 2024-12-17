package com.project.imunipet.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Entity not found for id: " + id);
    }   

    public ResourceNotFoundException(String id) {
        super("Entity not found for id: " + id);
    }   
}
