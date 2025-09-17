package com.evolv.care.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public ServerException(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ServerException error(EVOLV_ERROR error) {
        return new ServerException(error.code, error.message, error.httpStatus);
    }

    /**
     * error - method to append variables to the error message
     * @param error
     * @param args Error with placeholders replaced (e.g., {1}, {2}, etc.) in the Custom Error Message
     * @return
     */
    public static ServerException error(EVOLV_ERROR error, Object... args) {

        String formattedMessage = error.message;
        for (int i = 0; i < args.length; i++) {
            formattedMessage = formattedMessage.replace("{" + (i + 1) + "}", String.valueOf(args[i]));
        }

        return new ServerException(error.code, formattedMessage, error.httpStatus);
    }
}
