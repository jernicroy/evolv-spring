package com.evolv.care.app.exception;


import org.springframework.http.HttpStatus;

public enum EVOLV_ERROR {
    USER_NOT_FOUND("EU404", "{1} User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("EU400", "{1} User already exists, Please try with another name", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASS("U403", "Invalid Username or Password", HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_PRIVILEGES("E400", "Insufficient Access", HttpStatus.UNAUTHORIZED);


    final String code;
    final String message;

    final HttpStatus httpStatus;

    EVOLV_ERROR(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
