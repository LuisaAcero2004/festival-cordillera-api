package com.cordillera.festival.web.exception;

public enum ErrorMessages {

    ARTIST_NOT_FOUND("Artist not found with id: %s"),
    STAGE_NOT_FOUND("Stage not found with id: %s"),
    SHOW_NOT_FOUND("Show not found with id: %s");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(Object... args) {
        return String.format(this.message, args);
    }
}