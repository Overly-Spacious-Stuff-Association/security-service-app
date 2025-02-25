package org.example.securityservice.exception;

public class UserServiceOperationException extends UserServiceClientException {
    public UserServiceOperationException(String message) {
        super(message);
    }
}
