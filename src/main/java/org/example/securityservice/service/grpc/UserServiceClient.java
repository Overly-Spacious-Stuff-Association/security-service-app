package org.example.securityservice.service.grpc;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.securityservice.dto.request.RegisterRequestDto;
import org.example.securityservice.dto.response.RegistrationResponseDto;
import org.example.securityservice.exception.UserServiceClientException;
import org.example.securityservice.exception.UserServiceOperationException;
import org.example.securityservice.exception.UserServiceTimeoutException;
import org.example.securityservice.exception.UserServiceUnavailableException;
import org.springframework.stereotype.Component;
import user.CreateUserRequest;
import user.CreateUserResponse;
import user.UserServiceGrpc;
import java.util.concurrent.TimeUnit;

@Component
public class UserServiceClient {

    @GrpcClient("user_service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public RegistrationResponseDto createUser(RegisterRequestDto request) {
        try {
            CreateUserRequest grpcRequest = buildGrpcRequest(request);
            CreateUserResponse grpcResponse = executeGrpcCall(grpcRequest);

            return processGrpcResponse(grpcResponse);
        } catch (StatusRuntimeException e) {
            throw convertGrpcException(e);
        } catch (Exception e) {
            throw new UserServiceClientException("Ошибка при вызове UserService: " + e.getMessage());
        }
    }

    private CreateUserRequest buildGrpcRequest(RegisterRequestDto request) {
        return CreateUserRequest.newBuilder()
                .setEmail(request.getEmail())
                .setPasswordEncoded(request.getPassword())
                .setRoleId(request.getRoleId())
                .setFirstname(request.getFirstName())
                .setLastname(request.getLastName())
                .setBirthdate(request.getBirthDate().toString())
                .build();
    }

    private CreateUserResponse executeGrpcCall(CreateUserRequest request) {
        return userServiceStub
                .withDeadlineAfter(2, TimeUnit.SECONDS)
                .createUser(request);
    }

    private RegistrationResponseDto processGrpcResponse(CreateUserResponse response) {
        if (!response.getSuccess()) {
            throw new UserServiceOperationException(response.getMessage());
        }
        return RegistrationResponseDto.success(response.getMessage());
    }

    private RuntimeException convertGrpcException(StatusRuntimeException e) {
        return switch (e.getStatus().getCode()) {
            case DEADLINE_EXCEEDED -> new UserServiceTimeoutException("Таймаут соединения с UserService");
            case UNAVAILABLE -> new UserServiceUnavailableException("UserService недоступен");
            default -> new UserServiceClientException("Ошибка UserService: " + e.getStatus().getDescription());
        };
    }
}
