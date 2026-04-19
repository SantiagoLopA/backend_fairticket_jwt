package upb.edu.co.fairticket.domain.model;

import java.util.UUID;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.AllArgsConstructor;

import upb.edu.co.fairticket.domain.model.enums.Role;
import upb.edu.co.fairticket.domain.model.valueobjects.Email;

@Getter
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private Email email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String passwordHash;

    public static User createBuyer(String name, Email email,String passwordHash) {
        return new User(UUID.randomUUID(), name, email, Role.BUYER, LocalDateTime.now(), LocalDateTime.now(), passwordHash);
    }

    public static User createOrganizer(String name, Email email, String passwordHash) {
        return new User(UUID.randomUUID(), name, email, Role.ORGANIZER,
                LocalDateTime.now(), LocalDateTime.now(), passwordHash);
    }

    public static User createAdmin(String name, Email email, String passwordHash) {
        return new User(UUID.randomUUID(), name, email, Role.ADMIN,
                LocalDateTime.now(), LocalDateTime.now(), passwordHash);
    }

    public void updateProfile(String name, Email email) {
        this.name = name;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

    public boolean isOrganizer() {
        return this.role.equals(Role.ORGANIZER);
    }

    public boolean isBuyer() {
        return this.role.equals(Role.BUYER);
    }

}
