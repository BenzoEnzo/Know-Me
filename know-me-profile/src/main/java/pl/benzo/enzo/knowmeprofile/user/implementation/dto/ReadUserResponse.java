package pl.benzo.enzo.knowmeprofile.user.implementation.dto;


import pl.benzo.enzo.knowmeprofile.user.implementation.util.Gender;

public record ReadUserResponse(String name, String describe, Gender gender) {
}
