package com.onevizion.crudofbook.service.impl;

import com.onevizion.crudofbook.exception.BookException;
import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.ApiResult;
import com.onevizion.crudofbook.payload.AuthorStats;
import com.onevizion.crudofbook.repository.BookRepository;
import com.onevizion.crudofbook.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public ApiResult<List<Book>> getAll() {
        List<Book> bookList = bookRepository.findAllSortedDescByTitle();
        log.info("Found {} count of sorted by title desc books.", bookList.size());
        return bookList.isEmpty()
                ? ApiResult.errorResponse("Table Book is Empty!")
                : ApiResult.successResponse(bookList, "Founded count of book: " + bookList.size());
    }

    @Override
    public ApiResult<String> add(Book book) {
        if (!bookRepository.add(book))
            throw new BookException("Book not created!");
        log.info("Book is success created by Title: {}, Author: {}, Desc: {}",
                book.getTitle(), book.getAuthor(), book.getDescription());
        return ApiResult.successResponse("Book was created success!");
    }

    @Override
    public ApiResult<Map<String, List<Book>>> getAllByGroup() {
        List<Book> books = bookRepository.findAll();
        Map<String, List<Book>> booksGroupedByAuthor = books.stream().collect(Collectors.groupingBy(Book::getAuthor));
        log.info("Found {} authors of grouped.", booksGroupedByAuthor.size());
        return booksGroupedByAuthor.isEmpty()
                ? ApiResult.errorResponse("Books not found!")
                : ApiResult.successResponse(booksGroupedByAuthor, "OK");

    }

    @Override
    public ApiResult<List<AuthorStats>> getTopAuthors(char character) {
        String characterLowerCase = String.valueOf(character).toLowerCase();
        List<AuthorStats> authorStats = bookRepository.findTopAuthorsByCharacter(characterLowerCase);
        log.info("Found {} top 10 authorStats by character: '{}'.", authorStats.size(), characterLowerCase);
        return authorStats.isEmpty()
                ? ApiResult.errorResponse("Authors not found!")
                : ApiResult.successResponse(authorStats, "OK");
    }
}
