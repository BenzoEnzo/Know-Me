package pl.benzo.enzo.knowmeprofile.user.implementation.dto;

public record QueueJoinDto(boolean isInQueue,Long keyId,Long userId, boolean isInConversation) {
}
