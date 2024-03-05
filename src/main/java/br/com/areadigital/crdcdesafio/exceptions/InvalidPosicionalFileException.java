package br.com.areadigital.crdcdesafio.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPosicionalFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String status;

    public InvalidPosicionalFileException(String status, String message) {
        super(message);
        this.status = status;
    }

}