package pl.benzo.enzo.kmservicedto.profile;

public record AreaUserDto(Long id,Long userId, String name, Long keyId, boolean joined, boolean duringConversation, boolean isInQueue){


}
