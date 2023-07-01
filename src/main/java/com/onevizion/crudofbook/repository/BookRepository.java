package com.onevizion.crudofbook.repository;

import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.AuthorStats;

import java.util.List;

public interface BookRepository {

    List<Book> findAllSortedDescByTitle();

    boolean add(Book book);

    List<Book> findAll();

    List<AuthorStats> findTopAuthorsByCharacter(String characterLowerCase);
}
