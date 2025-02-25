package org.example.securityservice.exception;

public class UserServiceTimeoutException extends UserServiceClientException {
    public UserServiceTimeoutException(String message) {
        super(message);
    }
}
