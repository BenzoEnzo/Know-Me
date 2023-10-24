package pl.benzo.enzo.knowmeprofile.user.dto;


import pl.benzo.enzo.knowmeprofile.user.util.Gender;

public record UpdateUserResponse(Long id, String name, String describe, Gender gender) {
}
