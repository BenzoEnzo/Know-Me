package pl.benzo.enzo.kmservicedto.profile;


import pl.benzo.enzo.kmservicedto.shared.Gender;

public record UpdateUserRequest(Long id, String name, String describe, Gender gender) {
}
