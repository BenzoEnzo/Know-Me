package pl.benzo.enzo.kmservicedto.profile;


import pl.benzo.enzo.kmservicedto.shared.Gender;

public record ReadUserResponse(String name, String describe, Gender gender) {
}
