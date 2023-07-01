package com.onevizion.crudofbook.service;

import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.ApiResult;
import com.onevizion.crudofbook.payload.AuthorStats;

import java.util.List;
import java.util.Map;

public interface BookService {

    ApiResult<List<Book>> getAll();

    ApiResult<String> add(Book book);

    ApiResult<Map<String, List<Book>>> getAllByGroup();

    ApiResult<List<AuthorStats>> getTopAuthors(char character);
}
