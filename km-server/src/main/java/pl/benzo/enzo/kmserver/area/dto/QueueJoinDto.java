package pl.benzo.enzo.kmserver.area.dto;

public record QueueJoinDto(boolean isInQueue,Long keyId,Long userId, boolean isInConversation) {
}
