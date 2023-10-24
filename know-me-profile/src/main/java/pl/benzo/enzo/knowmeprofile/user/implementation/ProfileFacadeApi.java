package pl.benzo.enzo.knowmeprofile.user.implementation;

import pl.benzo.enzo.knowmeprofile.user.implementation.database.Key;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.*;

import java.util.List;
import java.util.Set;

public interface ProfileFacadeApi {
    SendCrypto generateCrypto();

    ReadUserResponse readUser(ReadUserRequest readUserRequest);
    ValidateCrypto validateAccount(SendCrypto sendCrypto);
    List<User> findAllUsers();

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    List<Key> findAllKeys();

    boolean saveKey(String name);

    Set<AreaUserDto> createArea(CreateAreaRequest createAreaRequest);

    QueueJoinDto addUserToQueue(AreaUserDto areaUserDto);

}
