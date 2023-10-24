package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.UserRepository;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ValidateCrypto;
import pl.benzo.enzo.knowmeprofile.user.implementation.mapper.UserMapper;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.DateOperation;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.GenerateID;

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
