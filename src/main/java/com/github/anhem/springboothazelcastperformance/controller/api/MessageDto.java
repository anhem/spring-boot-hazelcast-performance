package com.github.anhem.springboothazelcastperformance.controller.api;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MessageDto {

    public static final MessageDto MESSAGE_OK = new MessageDto("OK");

    private final String message;

    @JsonCreator
    public MessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
