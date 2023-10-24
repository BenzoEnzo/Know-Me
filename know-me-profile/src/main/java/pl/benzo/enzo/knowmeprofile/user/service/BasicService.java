package pl.benzo.enzo.knowmeprofile.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.database.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicService {
    private final UserRepository userRepository;
    public void createOrUpdateUser(User user){
        userRepository.save(user);
    }

    public User findUser(String crypto){
        return userRepository.findUserByCrypto(crypto);
    }
    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }


}
