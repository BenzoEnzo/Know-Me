package pl.benzo.enzo.kmserver.user.service;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.mapper.UserMapper;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.dto.UserDto;



@Service
@RequiredArgsConstructor
public class UserBaseService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public List<UserDto> readAll(){
        return List.ofAll(userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserDto)
        );
    }
    public void update(User user){
        userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
