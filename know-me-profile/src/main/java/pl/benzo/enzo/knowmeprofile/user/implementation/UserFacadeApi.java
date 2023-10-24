package pl.benzo.enzo.knowmeprofile.user.implementation;

import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.*;

import java.util.List;

public interface UserFacadeApi {
    SendCrypto generateCrypto();

    ReadUserResponse readUser(ReadUserRequest readUserRequest);
    ValidateCrypto validateAccount(SendCrypto sendCrypto);
    List<User> findAllUsers();

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
}
