package com.kaganmercan.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kaganmercan
 */
@RestControllerAdvice
public class GlobalHandlingException {
    // If there is no such data spring will catch...
    @ExceptionHandler(KaganMercanException.class)
    public String handlingNotFoundException(){
        return "No such data";
    }
    // If there is a null value spring will catch...
    @ExceptionHandler({NullPointerException.class})
    public String handlingNullPointerException(){
        return "Null value entered";
    }
}
