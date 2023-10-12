package pl.benzo.enzo.kmserver.user;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.CreateRequest;
import pl.benzo.enzo.kmserver.user.model.CreateResponse;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.UserRepository;
import pl.benzo.enzo.kmserver.util.DateOperation;
import pl.benzo.enzo.kmserver.util.GenerateID;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Scheduled(fixedRate = 360000)
    private void deleteExpiryUsers(){
        userRepository.deleteAllByDeleteAtBefore(LocalDateTime.now());
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public String saveUser(CreateRequest createRequest){
        final String crypto = GenerateID.create();
        final User user = User.builder()
                .name(createRequest.name())
                .describe(createRequest.describe())
                .gender(createRequest.gender())
                .crypto(crypto)
                .deleteAt(DateOperation.addHoursToDate(LocalDateTime.now(),24))
                .build();
        userRepository.save(user);
        return crypto;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
