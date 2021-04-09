package com.emt.lab.service.impl;

import com.emt.lab.exception.AuthorNotFoundException;
import com.emt.lab.exception.BookNotFoundException;
import com.emt.lab.model.Author;
import com.emt.lab.model.Book;
import com.emt.lab.repository.AuthorRepository;
import com.emt.lab.repository.BookRepository;
import com.emt.lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book create(Book book) {
        validateBookAndExtendWithAuthor(book);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        validateBookAndExtendWithAuthor(book);
        bookRepository.findById(book.getId()).orElseThrow(() -> new BookNotFoundException(book.getId()));
        return bookRepository.save(book);
    }

    private void validateBookAndExtendWithAuthor(Book book) {
        if (Objects.isNull(book) || Objects.isNull(book.getAuthor())) {
            throw new IllegalArgumentException();
        }
        Long authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return bookRepository.findAll(pageable);
    }

    @Override
    public void decreaseCopiesById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.decreaseCopies();
        bookRepository.save(book);
    }
}
