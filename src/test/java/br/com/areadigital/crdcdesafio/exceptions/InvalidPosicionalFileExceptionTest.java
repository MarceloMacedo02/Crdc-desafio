package br.com.areadigital.crdcdesafio.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvalidPosicionalFileExceptionTest {

    private String statusMock;

    @InjectMocks
    private InvalidPosicionalFileException exception;

    @Test
    public void testInvalidPosicionalFileException() {
        String expectedMessage = "Test message";

        statusMock = "Test status";

        exception = new InvalidPosicionalFileException(statusMock, expectedMessage);

        assertEquals(statusMock, exception.getStatus());
        assertEquals(expectedMessage, exception.getMessage());
    }
}