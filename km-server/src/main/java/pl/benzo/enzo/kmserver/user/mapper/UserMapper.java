package pl.benzo.enzo.kmserver.user.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.dto.UserDto;

@Component
@NoArgsConstructor
public class UserMapper {
    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(),user.getName());
    }
}