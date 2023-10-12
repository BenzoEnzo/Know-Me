package pl.benzo.enzo.kmserver.user;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.*;
import pl.benzo.enzo.kmserver.util.DateOperation;
import pl.benzo.enzo.kmserver.util.GenerateID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = 360000)
    private void deleteExpiryUsers(){
        userRepository.deleteAllByDeleteAtBefore(LocalDateTime.now());
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User saveUser(CreateRequest createRequest){
        final User user = User.builder()
                .name(createRequest.name())
                .describe(createRequest.describe())
                .gender(createRequest.gender())
                .build();
       return userRepository.save(user);
    }
    public IdDto generateUser(){
        final User user = User.builder()
                .crypto(GenerateID.create())
                .deleteAt(DateOperation.addHoursToDate(LocalDateTime.now(),24))
                .build();
        userRepository.save(user);
        return new IdDto(user.getCrypto());
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public ValidateResponse validateIdUser(IdDto crypto){
        final User user = userRepository.findUserByCrypto(crypto.crypto());
        if(Objects.isNull(user)){
            throw new IllegalArgumentException("Invalid Credentials");
        }
        return new ValidateResponse(user.getId(),"");
    }
}
