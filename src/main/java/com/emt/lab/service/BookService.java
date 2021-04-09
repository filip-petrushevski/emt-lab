package com.emt.lab.service;

import com.emt.lab.model.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book create(Book book);

    void deleteById(Long id);

    Book update(Book book);

    Page<Book> getAll(int page, int size);

    void decreaseCopiesById(Long id);

}
