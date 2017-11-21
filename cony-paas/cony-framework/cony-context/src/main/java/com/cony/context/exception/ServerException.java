package com.cony.context.exception;

/**
 */
public class ServerException extends RuntimeException {
    public static final String DEFAULT_ERROR_CODE = "ServerException";

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
