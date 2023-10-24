package pl.benzo.enzo.knowmeprofile.user;

import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.dto.ValidateCrypto;

import java.util.List;

public interface UserFacadeApi {
    void createAccount(SendCrypto sendCrypto);
    ValidateCrypto validateAccount(SendCrypto sendCrypto);
    List<User> findAllUsers();
}
