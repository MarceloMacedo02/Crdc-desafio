package br.com.areadigital.crdcdesafio.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPosicionalFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String status;
    private List<ErrorMessage> errors;

    public InvalidPosicionalFormatException(String status, String message, List<ErrorMessage> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

}