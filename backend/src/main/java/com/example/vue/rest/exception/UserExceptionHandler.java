package com.example.vue.rest.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.vue.rest.dto.Response;

/**
 * @author o.myltsyn * @since 18 дек. 2019 г.
 */
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Response> handleException(EntityNotFoundException e) {
        return new ResponseEntity<>(
                Response.builder()
                        .response(e.getLocalizedMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
