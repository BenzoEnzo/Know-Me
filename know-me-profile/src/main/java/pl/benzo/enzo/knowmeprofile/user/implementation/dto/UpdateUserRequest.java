package pl.benzo.enzo.knowmeprofile.user.implementation.dto;


import pl.benzo.enzo.knowmeprofile.user.implementation.util.Gender;

public record UpdateUserRequest(Long id, String name, String describe, Gender gender) {
}
