package com.example.server.exceptons;

import lombok.Getter;

@Getter
public class WebException extends Exception{
    private String message;
    private String messageKh;
    private String code;

    public WebException() {

    }

    public WebException(String message, String messageKh, String code) {
        super(message);
        this.message = message;
        this.messageKh = messageKh;
        this.code = code;
    }
}
