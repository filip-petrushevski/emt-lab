package com.emt.lab.exception;

public class BookNotFoundException extends NotFoundException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id=%d is not found", id));
    }
}
