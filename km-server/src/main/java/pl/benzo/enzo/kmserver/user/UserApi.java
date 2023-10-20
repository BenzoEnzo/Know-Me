package pl.benzo.enzo.kmserver.user;

import io.vavr.control.Try;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.dto.*;

import io.vavr.collection.List;

import java.io.FileNotFoundException;


public interface UserApi {
    Try<List<UserDto>> getAll();
    Try<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest);
    Try<CryptoDto> generateCrypto();
    Try<ValidateUserResponse> validateUser(CryptoDto crypto);
    Try<ReadUserResponse> readUser(ReadUserRequest readUserRequest);
    void uploadImageOnServ(MultipartFile file, Long userId);
    Resource loadFile(String fileName) throws FileNotFoundException;
}
