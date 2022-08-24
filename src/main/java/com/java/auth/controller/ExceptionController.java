package com.java.auth.controller;
import com.java.auth.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
 public class ExceptionController extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = UserException.class)
        public ResponseEntity<Object> handleUserException(
            Exception ex, WebRequest request) {
            String bodyOfResponse = "Verifique os parametros passados. " + (ex.getMessage() == null ? "" : ex.getMessage());
            return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler(value = UserEmailException.class)
    public ResponseEntity<Object> handleEmailUserException(
            Exception ex, WebRequest request) {
        String bodyOfResponse = "Esse email já foi utilizado" + (ex.getMessage() == null ? "" : ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserInvalidException.class)
    public ResponseEntity<Object> handleUserInvalidoException(
           Exception ex, WebRequest request) {
        String bodyOfResponse = "User inválido, verifique as informações e tente novamente. " + (ex.getMessage() == null ? "" : ex.getMessage());        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNaoEncontradoException(
           Exception ex, WebRequest request) {
        String bodyOfResponse = "User não encontrado. Verifique as informações e tente novamente. " + (ex.getMessage() == null ? "" : ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = UserNotExistentException.class)
    public ResponseEntity<Object> handleUserExistenteException(
            Exception ex, WebRequest request) {
        Object bodyOfResponse = "Email ou username já existentes, verifique outros. " + (ex.getMessage() == null ? "" : ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
