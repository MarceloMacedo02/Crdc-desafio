package br.com.areadigital.crdcdesafio.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorMessageTest {

    @Test
    void testConstructorWithInt() {
        int linha = 1;
        String message = "Test message";
        ErrorMessage errorMessage = new ErrorMessage(linha, message);
        assertEquals("1", errorMessage.getLine());
        assertEquals(message, errorMessage.getError());
    }

    @Test
    void testConstructorWithString() {
        String linha = "2";
        String message = "Test message";
        ErrorMessage errorMessage = new ErrorMessage(linha, message);
        assertEquals(linha, errorMessage.getLine());
        assertEquals(message, errorMessage.getError());
    }

    @Test
    void testGettersAndSetters() {
        ErrorMessage errorMessage = new ErrorMessage(1, "Test message");
        errorMessage.setLine("3");
        errorMessage.setError("New test message");
        assertEquals("3", errorMessage.getLine());
        assertEquals("New test message", errorMessage.getError());
    }

    @Test
    void testEquals() {
        ErrorMessage errorMessage1 = new ErrorMessage(1, "Test message");
        ErrorMessage errorMessage2 = new ErrorMessage(1, "Test message");
        ErrorMessage errorMessage3 = new ErrorMessage(2, "Test message");

        assertTrue(errorMessage1.equals(errorMessage2));
        assertFalse(errorMessage1.equals(errorMessage3));
    }

    @Test
    void testHashCode() {
        ErrorMessage errorMessage1 = new ErrorMessage(1, "Test message");
        ErrorMessage errorMessage2 = new ErrorMessage(1, "Test message");

        assertEquals(errorMessage1.hashCode(), errorMessage2.hashCode());
    }

    @Test
    void testToString() {
        ErrorMessage errorMessage = new ErrorMessage(1, "Test message");
        assertEquals("ErrorMessage(line=1, error=Test message)", errorMessage.toString());
    }
}