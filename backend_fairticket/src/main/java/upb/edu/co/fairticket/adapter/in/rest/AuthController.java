package upb.edu.co.fairticket.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.edu.co.fairticket.infrastructure.security.JwtService;
import upb.edu.co.fairticket.adapter.in.rest.dto.request.LoginRequest;
import upb.edu.co.fairticket.adapter.in.rest.dto.request.RegisterUserRequest;
import upb.edu.co.fairticket.adapter.in.rest.dto.response.AuthResponse;

import upb.edu.co.fairticket.domain.usecase.user.LoginUseCase;
import upb.edu.co.fairticket.domain.usecase.user.RegisterUserUseCase;
import upb.edu.co.fairticket.domain.model.User;

@RestController
@RequestMapping("/api/gateway")

@RequiredArgsConstructor

public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final JwtService jwtService; 

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserRequest request) {

        User user = switch (request.role().toUpperCase()) {
            case "ORGANIZER" -> registerUserUseCase.registerOrganizer(
                    request.name(),
                    request.email(),
                    request.password()
            );
            default -> registerUserUseCase.registerBuyer(
                    request.name(),
                    request.email(),
                    request.password()
            );
        };

        String token = jwtService.generateToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = loginUseCase.login(
                request.email(),
                request.password()
        );

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}