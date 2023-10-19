package pl.benzo.enzo.kmserver.area.dto;

import pl.benzo.enzo.kmserver.area.Area;
import pl.benzo.enzo.kmserver.user.model.User;

public record AreaUserDto(Long id, String name, Long keyId, boolean joined, boolean duringConversation, boolean isInQueue){


}
