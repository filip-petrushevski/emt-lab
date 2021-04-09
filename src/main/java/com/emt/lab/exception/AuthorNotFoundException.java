package com.emt.lab.exception;

public class AuthorNotFoundException extends NotFoundException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id=%d is not found", id));
    }
}
