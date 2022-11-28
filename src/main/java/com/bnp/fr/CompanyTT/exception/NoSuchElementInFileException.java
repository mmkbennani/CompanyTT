package com.bnp.fr.CompanyTT.exception;

public class NoSuchElementInFileException  extends RuntimeException {
    private String message;

    public NoSuchElementInFileException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}