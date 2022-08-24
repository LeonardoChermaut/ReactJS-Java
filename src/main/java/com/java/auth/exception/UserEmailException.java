package com.java.auth.exception;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
public class UserEmailException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserEmailException(String message) {
        super(message);
    }

    public UserEmailException(String message, LocalDateTime timeStamp) {
        super(message, timeStamp);
    }

}
