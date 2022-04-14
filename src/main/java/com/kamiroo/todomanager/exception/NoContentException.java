package com.kamiroo.todomanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {
    public NoContentException() {
        super();
    }

    public NoContentException(String message) {
        super(message);
    }
}
