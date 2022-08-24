package com.java.auth.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
    private LocalDateTime timeStamp;

    public UserException(String message) {
        super(message);
        this.message = message;
    }

    public UserException(String message, LocalDateTime timeStamp) {
        super(message);
        this.message = message;
        this.timeStamp = timeStamp;
    }

}
