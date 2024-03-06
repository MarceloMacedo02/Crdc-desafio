package br.com.areadigital.crdcdesafio.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
 

class InvalidPosicionalFormatExceptionTest {

    @Test
    void testConstructorAndGetter() {
        String status = "error";
        String message = "Test message";
        List<ErrorMessage> errors = new ArrayList();
        InvalidPosicionalFormatException exception = new InvalidPosicionalFormatException(status, message, errors);
        assertEquals(message, exception.getMessage());
        assertEquals(status, exception.getStatus());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testConstructorAndGetterWithMock() {
        String status = "error";
        String message = "Test message";
        List<ErrorMessage> errors = new ArrayList<>();
        InvalidPosicionalFormatException exception = new InvalidPosicionalFormatException(status, message, errors);
        exception.setStatus(status);
        assertEquals(status, exception.getStatus());
    }
}