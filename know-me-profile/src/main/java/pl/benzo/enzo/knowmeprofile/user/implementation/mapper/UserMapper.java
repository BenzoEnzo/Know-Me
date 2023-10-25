package pl.benzo.enzo.knowmeprofile.user.implementation.mapper;


import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmservicedto.profile.SendCrypto;
import pl.benzo.enzo.kmservicedto.profile.ValidateCrypto;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;


@Component
public class UserMapper {
    public User sendCryptoMapping(SendCrypto sendCrypto){
        return User.builder().crypto(sendCrypto.crypto()).build();
    }

    public ValidateCrypto validateCryptoMapping(User user){
        return new ValidateCrypto(user.getId());
    }
}
