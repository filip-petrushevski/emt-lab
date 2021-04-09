package com.emt.lab.api;

import com.emt.lab.enums.BookCategory;
import com.emt.lab.model.Book;
import com.emt.lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public List<BookCategory> fetchBookCategories() {
        return List.of(BookCategory.values());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Book> fetchBookPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        return bookService.getAll(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/decreaseCopies")
    public void decreaseCopiesById(@PathVariable long id) {
        bookService.decreaseCopiesById(id);
    }


}
