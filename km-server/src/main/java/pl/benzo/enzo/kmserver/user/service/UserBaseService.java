package pl.benzo.enzo.kmserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.mapper.UserMapper;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserBaseService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public List<UserDto> readAll(){
        final List<User> users = userRepository.findAll();
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserDto).toList();
    }
    public void update(User user){
        userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseGet(null);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
