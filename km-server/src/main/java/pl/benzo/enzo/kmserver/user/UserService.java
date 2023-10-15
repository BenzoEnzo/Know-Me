package pl.benzo.enzo.kmserver.user;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.*;
import pl.benzo.enzo.kmserver.util.DateOperation;
import pl.benzo.enzo.kmserver.util.GenerateID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Jwt jwt;

    public UserService(UserRepository userRepository, Jwt jwt) {
        this.userRepository = userRepository;
        this.jwt = jwt;
    }

    @Scheduled(fixedRate = 360000)
    private void deleteExpiryUsers(){
        userRepository.deleteAllByDeleteAtBefore(LocalDateTime.now());
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User saveUser(CreateRequest createRequest, String token){
        String crypto = jwt.extractUsername(token);
        User user = userRepository.findUserByCrypto(crypto);
        User.builder()
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = Optional.ofNullable(userRepository.findUserByCrypto(username));

        return userDetail.map(ImplUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
