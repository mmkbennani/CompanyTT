package com.bnp.fr.CompanyTT.exception;

public class DayValidationException extends RuntimeException {

    private String message;

    public DayValidationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
