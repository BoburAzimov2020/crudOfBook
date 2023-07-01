package com.onevizion.crudofbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class Book {

    @JsonProperty("id")
    @NotNull
    private Integer id;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("author")
    @NotNull
    private String author;

    @JsonProperty("description")
    @NotNull
    private String description;

}
