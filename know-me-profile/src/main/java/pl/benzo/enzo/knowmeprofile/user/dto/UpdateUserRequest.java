package pl.benzo.enzo.knowmeprofile.user.dto;


import pl.benzo.enzo.knowmeprofile.user.util.Gender;

public record UpdateUserRequest(Long id, String name, String describe, Gender gender) {
}
