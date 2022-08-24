package com.java.auth.exception;

import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@NoArgsConstructor
public class UserNotExistentException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserNotExistentException(String message) {
        super(message);
    }

    public UserNotExistentException(String message, LocalDateTime timeStamp) {
        super(message, timeStamp);
    }

}
