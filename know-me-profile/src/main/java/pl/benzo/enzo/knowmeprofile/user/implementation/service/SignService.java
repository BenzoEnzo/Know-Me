package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.UserRepository;
import pl.benzo.enzo.kmservicedto.profile.SendCrypto;
import pl.benzo.enzo.kmservicedto.profile.ValidateCrypto;
import pl.benzo.enzo.knowmeprofile.user.implementation.mapper.UserMapper;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.DateOperation;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.GenerateID;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SignService extends BasicService{

    private final UserMapper userMapper;
    private final KafkaLogService kafkaLogService;
    public SignService(UserRepository userRepository, UserMapper userMapper, KafkaLogService kafkaLogService) {
        super(userRepository);
        this.userMapper = userMapper;
        this.kafkaLogService = kafkaLogService;
    }

    public ValidateCrypto validateCrypto(SendCrypto sendCrypto){
        final User user = findUser(sendCrypto.crypto());

        if(user != null){
            return userMapper.validateCryptoMapping(user);
        } else throw new IllegalArgumentException("User doesnt exist");
    }

    public SendCrypto generateCrypto() {
            final int id = findAllUsers().size() + 1;

            final User user = User.builder()
                    .crypto(GenerateID.create())
                    .name("DUCH_" + id)
                    .describe("Tutaj wpisz swój opis.....")
                    .deleteAt(DateOperation.addHoursToDate(LocalDateTime.now(),24))
                    .build();

            createOrUpdateUser(user);

            kafkaLogService.sendLog("Stworzono cryptoUsera o ID = " + id);

            return new SendCrypto(user.getCrypto());
    }
}
