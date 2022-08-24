package com.java.auth.exception;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
public class UserNotFoundException extends UserException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, LocalDateTime timeStamp) {
        super(message, timeStamp);
    }

}
