package com.slzvieira.acme.controller;

import com.slzvieira.acme.exception.AlreadyExistsException;
import com.slzvieira.acme.exception.InvalidDataException;
import com.slzvieira.acme.exception.NotFoundException;
import com.slzvieira.acme.model.AcmeFault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<AcmeFault> handleException(Throwable e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        AcmeFault error = AcmeFault.builder()
                .code(status.value())
                .type(status.name())
                .build()
                .addMessage("Internal error. Please, contact the administrator.");
        log.error("Internal error: ", e);
        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<AcmeFault> handleException(NotFoundException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        AcmeFault error = AcmeFault.builder()
                .code(status.value())
                .type(status.name())
                .build()
                .addMessage(e.getMessage());

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler({ InvalidDataException.class })
    public ResponseEntity<AcmeFault> handleException(InvalidDataException e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        AcmeFault error = AcmeFault.builder()
                .code(status.value())
                .type(status.name())
                .messages(e.getErrorMessages())
                .build();

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler({ AlreadyExistsException.class, NoResourceFoundException.class })
    public ResponseEntity<AcmeFault> handleException(Exception e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        AcmeFault error = AcmeFault.builder()
                .code(status.value())
                .type(status.name())
                .build()
                .addMessage(e.getMessage());

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<AcmeFault> handleException(MethodArgumentNotValidException e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> "Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        AcmeFault error = AcmeFault.builder()
                .code(status.value())
                .type(status.name())
                .messages(errorMessages)
                .build();

        return ResponseEntity
                .status(status)
                .body(error);
    }
}
