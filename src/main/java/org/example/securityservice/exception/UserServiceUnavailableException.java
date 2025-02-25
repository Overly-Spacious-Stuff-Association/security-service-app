package org.example.securityservice.exception;

public class UserServiceUnavailableException extends UserServiceClientException {
    public UserServiceUnavailableException(String message) {
        super(message);
    }
}
