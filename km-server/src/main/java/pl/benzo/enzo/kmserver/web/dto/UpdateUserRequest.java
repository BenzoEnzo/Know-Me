package pl.benzo.enzo.kmserver.web.dto;




public record UpdateUserRequest(Long id, String name, String describe,Gender gender) {
}
