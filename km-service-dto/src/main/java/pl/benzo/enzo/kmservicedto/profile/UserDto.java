package pl.benzo.enzo.kmservicedto.profile;

import pl.benzo.enzo.kmservicedto.shared.Gender;

public record UserDto(Long id, String name, Gender gender) {
}
