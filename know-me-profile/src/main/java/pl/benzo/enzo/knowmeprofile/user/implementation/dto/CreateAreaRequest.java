package pl.benzo.enzo.knowmeprofile.user.implementation.dto;


import pl.benzo.enzo.knowmeprofile.user.implementation.database.Key;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;

public record CreateAreaRequest(User user, Key key) {
}
