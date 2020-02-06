package com.rom.works.vocabular.advice;

import com.rom.works.vocabular.exception.DefinitionNotFoundException;
import com.rom.works.vocabular.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VocabularExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DefinitionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String definitionNotFoundHandler(DefinitionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }
}
