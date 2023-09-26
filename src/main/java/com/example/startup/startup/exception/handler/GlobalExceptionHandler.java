package com.example.startup.startup.exception.handler;



import com.example.startup.startup.exception.AccessForbiddenException;
import com.example.startup.startup.exception.UnAuthorizeException;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.exception.errorResponse.InvalidInputErrorResponse;
import com.example.startup.startup.exception.BadRequestException;
import com.example.startup.startup.exception.NotFoundException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<InvalidInputErrorResponse> handleRequestParameterException(MethodArgumentNotValidException e) {
        String message = "Invalid inputs";
        Integer statusCode = HttpStatus.BAD_REQUEST.value();

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new InvalidInputErrorResponse(message, statusCode,errors),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizeException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleUnAuthorizeException(Exception e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleAccessForbiddenException(Exception e) {
       return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleAccessDeniedException(Exception e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleAuthenticationException(Exception e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleUnReadableRequest(HttpMessageNotReadableException e) {
        String message = "Invalid input: Non numeric value found at numeric field";
        return new ResponseEntity<>(new SimpleResponseRest(message, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<SimpleResponseRest> handleUnexpectedTypeException(UnexpectedTypeException e) {
        return new ResponseEntity<>(new SimpleResponseRest(e.getMessage().trim(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
