package pl.benzo.enzo.knowmeprofile.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.database.UserRepository;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.dto.ValidateCrypto;
import pl.benzo.enzo.knowmeprofile.user.mapper.UserMapper;

@Service
@Slf4j
public class SignService extends BasicService{

    private final UserMapper userMapper;

    public SignService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
    }

    public void createAccount(SendCrypto sendCrypto){
        final User user = User.builder()
                .crypto(sendCrypto.crypto())
                .build();
        createUser(user);
    }

    public ValidateCrypto validateCrypto(SendCrypto sendCrypto){
        final User user = findUser(sendCrypto.crypto());
        if(user != null){
            return userMapper.validateCryptoMapping(user);
        } else throw new IllegalArgumentException("User doesnt exist");
    }
}
