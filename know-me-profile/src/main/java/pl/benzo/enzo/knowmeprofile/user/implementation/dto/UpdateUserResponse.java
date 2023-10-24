package pl.benzo.enzo.knowmeprofile.user.implementation.dto;


import pl.benzo.enzo.knowmeprofile.user.implementation.util.Gender;

public record UpdateUserResponse(Long id, String name, String describe, Gender gender) {
}
