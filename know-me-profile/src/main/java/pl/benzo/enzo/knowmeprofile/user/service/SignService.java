package pl.benzo.enzo.knowmeprofile.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.database.UserRepository;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.dto.ValidateCrypto;
import pl.benzo.enzo.knowmeprofile.user.mapper.UserMapper;
import pl.benzo.enzo.knowmeprofile.user.util.DateOperation;
import pl.benzo.enzo.knowmeprofile.user.util.GenerateID;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SignService extends BasicService{

    private final UserMapper userMapper;

    public SignService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
    }

    public ValidateCrypto validateCrypto(SendCrypto sendCrypto){
        final User user = findUser(sendCrypto.crypto());

        if(user != null){
            return userMapper.validateCryptoMapping(user);
        } else throw new IllegalArgumentException("User doesnt exist");
    }

    public SendCrypto generateCrypto() {
            final User user = User.builder()
                    .crypto(GenerateID.create())
                    .deleteAt(DateOperation.addHoursToDate(LocalDateTime.now(),24))
                    .build();

            createOrUpdateUser(user);

            return new SendCrypto(user.getCrypto());
    }
}
