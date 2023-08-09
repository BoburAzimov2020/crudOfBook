package com.onevizion.crudofbook.controller;

import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.ApiResult;
import com.onevizion.crudofbook.payload.AuthorStats;
import com.onevizion.crudofbook.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public ApiResult<List<Book>> getAll() {
        log.info("GET Request to get all books sorted desc by title.");
        return bookService.getAll();
    }

    @Override
    public ApiResult<String> add(Book book) {
        log.info("POST Request to add new Book: {}", book);
        return bookService.add(book);
    }

    @Override
    public ApiResult<Map<String, List<Book>>> getAllByGroup() {
        log.info("GET Request to get all by group.");
        return bookService.getAllByGroup();
    }

    @Override
    public ApiResult<List<AuthorStats>> getTopAuthors(char character) {
        log.info("GET Request to get top authors by character: {}.", character);
        return bookService.getTopAuthors(character);
    }

    @Override
    public ApiResult<Book> getFirst() {
        // TODO Auto-generated method stub - Some additional text for test.
        throw new UnsupportedOperationException("Unimplemented method 'getFirst'");
    }

    // MAIN TEST COMMIT !!!
    // Some TEST COMMIT ----------->>>>>>>>>>>>>
}
