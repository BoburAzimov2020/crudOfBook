package com.onevizion.crudofbook.repository.impl;

import com.onevizion.crudofbook.model.Book;
import com.onevizion.crudofbook.payload.AuthorStats;
import com.onevizion.crudofbook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String GET_ALL_BOOKS = "SELECT * FROM book";
    private final static String GET_ALL_BOOKS_SORTED_BY_TITLE_DESC = "SELECT * FROM book ORDER BY LOWER(title) DESC";
    private final static String ADD_BOOK = "INSERT INTO book (title, author, description) VALUES (?, ?, ?)";
    private final static String SELECT_TOP_AUTHORS_BY_CHARACTER = "SELECT author, COUNT(id) AS count FROM book" +
            " WHERE LOWER(title) LIKE ? GROUP BY author ORDER BY count DESC LIMIT 10";

    @Override
    public List<Book> findAllSortedDescByTitle() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SORTED_BY_TITLE_DESC, new BookMapper());
    }

    @Override
    public boolean add(Book book) {
        int rowsAffected = jdbcTemplate.update(
                ADD_BOOK,
                book.getTitle(), book.getAuthor(), book.getDescription());
        return rowsAffected > 0;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(GET_ALL_BOOKS, new BookMapper());
    }

    @Override
    public List<AuthorStats> findTopAuthorsByCharacter(String characterLowerCase) {
        String likePattern = "%" + characterLowerCase + "%";
        return jdbcTemplate.query(
                SELECT_TOP_AUTHORS_BY_CHARACTER,
                new Object[]{likePattern},
                (rs, rowNum) -> {
                    String author = rs.getString("author");
                    int count = rs.getInt("count");
                    return new AuthorStats(author, count);
                }
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setDescription(rs.getString("description"));
            return book;
        }
    }

}
