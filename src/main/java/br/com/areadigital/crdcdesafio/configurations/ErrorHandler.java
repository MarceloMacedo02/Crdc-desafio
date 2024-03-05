package br.com.areadigital.crdcdesafio.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.areadigital.crdcdesafio.exceptions.ErrorMessage;
import br.com.areadigital.crdcdesafio.exceptions.InvalidPosicionalFileException;
import br.com.areadigital.crdcdesafio.exceptions.InvalidPosicionalFormatException;
import lombok.Data;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidPosicionalFileException.class)
    public ResponseEntity<SimpleErrorResponse> handleInvalidPosicionalFileException(InvalidPosicionalFileException ex) {
        SimpleErrorResponse errorResponse = new SimpleErrorResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPosicionalFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPosicionalFormatException(InvalidPosicionalFormatException ex) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        for (ErrorMessage errorMessage : ex.getErrors()) {
            errorMessages.add(new ErrorMessage(errorMessage.getLine(), errorMessage.getError()));
        }
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage(), errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleErrorResponse> handleGenericException(Exception ex) {
        SimpleErrorResponse errorResponse = new SimpleErrorResponse("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class ErrorResponse {
        private String status;
        private String message;
        private List<ErrorMessage> errors;

        public ErrorResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public ErrorResponse(String status, String message, List<ErrorMessage> errors) {
            this.status = status;
            this.message = message;
            this.errors = errors;
        }
    }

    @Data
    public static class SimpleErrorResponse {
        private String status;
        private String message;

        public SimpleErrorResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

    }

}