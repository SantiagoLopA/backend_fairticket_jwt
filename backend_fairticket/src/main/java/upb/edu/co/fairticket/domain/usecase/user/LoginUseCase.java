package upb.edu.co.fairticket.domain.usecase.user;

import lombok.RequiredArgsConstructor;
import upb.edu.co.fairticket.domain.port.PasswordProtector;
import upb.edu.co.fairticket.domain.model.valueobjects.Email;
import upb.edu.co.fairticket.domain.port.UserRepository;
import upb.edu.co.fairticket.domain.model.User;
import upb.edu.co.fairticket.domain.exception.DomainException;

@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordProtector passwordProtector;

    public User login(String emailStr, String password) {

    Email email = new Email(emailStr);

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new DomainException("User not found"));

    if (!passwordProtector.matches(password, user.getPasswordHash())) {
        throw new DomainException("Invalid credentials");
    }

    return user;
    }
}