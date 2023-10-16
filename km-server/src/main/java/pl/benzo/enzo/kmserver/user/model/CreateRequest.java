package pl.benzo.enzo.kmserver.user.model;

public record CreateRequest(Long id,String name, String describe, Gender gender) {
}
