package pl.benzo.enzo.knowmeprofile.user;

import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.dto.*;

import java.util.List;

public interface UserFacadeApi {
    SendCrypto generateCrypto();

    ReadUserResponse readUser(ReadUserRequest readUserRequest);
    ValidateCrypto validateAccount(SendCrypto sendCrypto);
    List<User> findAllUsers();

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
}
