package pl.benzo.enzo.kmserver.web.dto;

public record QueueJoinDto(boolean isInQueue,Long keyId,Long userId, boolean isInConversation) {
}
