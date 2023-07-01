package com.onevizion.crudofbook.controller;

import com.onevizion.crudofbook.constants.RestConstants;
import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.ApiResult;
import com.onevizion.crudofbook.payload.AuthorStats;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping(BookController.BOOK_REST_PATH)
public interface BookController {

    String BOOK_REST_PATH = RestConstants.BASE_PATH_V1 + "book/";
    String GET_ALL_BOOK_SORTED_BY_TITLE = "all-sorted";
    String ADD_BOOK = "add";
    String GET_ALL_GROUPED_BY_AUTHOR = "grouped-by-author";
    String GET_TOP_AUTHORS_BY_CHARACTER = "top-authors-by-char";

    @GetMapping(GET_ALL_BOOK_SORTED_BY_TITLE)
    ApiResult<List<Book>> getAll();

    @PostMapping(ADD_BOOK)
    ApiResult<String> add(@Valid @RequestBody Book book);

    @GetMapping(GET_ALL_GROUPED_BY_AUTHOR)
    ApiResult<Map<String, List<Book>>> getAllByGroup();

    @GetMapping(GET_TOP_AUTHORS_BY_CHARACTER)
    ApiResult<List<AuthorStats>> getTopAuthors(@RequestParam("character") char character);
}
