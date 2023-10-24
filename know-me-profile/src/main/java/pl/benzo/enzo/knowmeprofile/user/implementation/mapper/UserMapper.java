package pl.benzo.enzo.knowmeprofile.user.implementation.mapper;


import org.springframework.stereotype.Component;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ValidateCrypto;

@Component
public class UserMapper {
    public User sendCryptoMapping(SendCrypto sendCrypto){
        return User.builder().crypto(sendCrypto.crypto()).build();
    }

    public ValidateCrypto validateCryptoMapping(User user){
        return new ValidateCrypto(user.getId());
    }
}
