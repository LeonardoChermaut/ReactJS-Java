package com.java.auth.exception;

import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
public class UserInvalidException extends UserException {

	private static final long serialVersionUID = 1L;

	public UserInvalidException(String message) {
        super(message);
    }

    public UserInvalidException(String message, LocalDateTime timeStamp) {
        super(message, timeStamp);
    }
    
}
