package com.onevizion.crudofbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorStats {

    private String author;
    private int count;
}
