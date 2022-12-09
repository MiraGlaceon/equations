package mira.space.controller.exception.advice;

import mira.space.controller.exception.RootsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RootsNotFoundAdvice {
    @ExceptionHandler(RootsNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String rootsNotFoundHandler(RootsNotFoundException ex) {
        return ex.getMessage();
    }
}
