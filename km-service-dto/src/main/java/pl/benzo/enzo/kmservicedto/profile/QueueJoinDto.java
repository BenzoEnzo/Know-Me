package pl.benzo.enzo.kmservicedto.profile;

public record QueueJoinDto(boolean isInQueue,Long keyId,Long userId, boolean isInConversation) {
}
