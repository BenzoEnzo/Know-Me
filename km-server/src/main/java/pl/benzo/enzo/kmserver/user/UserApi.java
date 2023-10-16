package pl.benzo.enzo.kmserver.user;

import io.vavr.control.Try;
import pl.benzo.enzo.kmserver.user.model.dto.*;

import io.vavr.collection.List;
import java.util.Optional;


public interface UserApi {
    Try<List<UserDto>> getAll();
    Try<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest);
    Try<CryptoDto> generateCrypto();
    Try<ValidateUserResponse> validateUser(CryptoDto crypto);
    Try<ReadUserResponse> readUser(ReadUserRequest readUserRequest);
}
