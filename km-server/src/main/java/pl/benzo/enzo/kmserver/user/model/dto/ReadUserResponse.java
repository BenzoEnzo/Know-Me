package pl.benzo.enzo.kmserver.user.model.dto;

import pl.benzo.enzo.kmserver.user.model.Gender;

public record ReadUserResponse(String name, String describe, Gender gender) {
}
