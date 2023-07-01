package com.onevizion.crudofbook.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private Boolean success = false;
    private String message;
    private T data;
    private List<String> errors;


    //SUCCESS RESPONSE WITH DATA AND MESSAGE
    public ApiResult(T data, Boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    //SUCCESS OR ERROR RESPONSE WITH MESSAGE
    public ApiResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResult(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
        this.success = Boolean.FALSE;
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(data, true, message);
    }

    public static ApiResult<String> successResponse(String message) {
        return new ApiResult<>(message, true);
    }

    public static <E> ApiResult<E> errorResponse(String errorMessage) {
        return new ApiResult<>(errorMessage, false);
    }

    public static <E> ApiResult<E> errorResponse(String errorMessage, List<String> errors) {
        return new ApiResult<>(errorMessage, errors);
    }

}
