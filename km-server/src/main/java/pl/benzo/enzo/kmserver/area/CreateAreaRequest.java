package pl.benzo.enzo.kmserver.area;

import pl.benzo.enzo.kmserver.key.Key;
import pl.benzo.enzo.kmserver.user.model.User;

public record CreateAreaRequest(User user, Key key) {
}
