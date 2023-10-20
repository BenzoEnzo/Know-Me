package pl.benzo.enzo.kmserver.user.service;



import io.vavr.control.Try;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.UserApi;
import pl.benzo.enzo.kmserver.user.mapper.UserMapper;
import pl.benzo.enzo.kmserver.user.model.*;
import pl.benzo.enzo.kmserver.user.model.dto.*;
import pl.benzo.enzo.kmserver.util.DateOperation;
import pl.benzo.enzo.kmserver.util.GenerateID;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserApi {

    private final UserRepository userRepository;
    private final UserBaseService userBaseService;
    private final UserMapper userMapper;
    private final Jwt jwt;
    private String filename;
    private final UploadService uploadService = new UploadService();

    public Try<ReadUserResponse> readUser(ReadUserRequest readUserRequest){
        return Try.of(()-> {
            final User user = userRepository.findById(readUserRequest.id()).orElseGet(null);

            return new ReadUserResponse(user.getName(), user.getDescribe(),user.getGender());
        });
    }
    @Override
    public Try<io.vavr.collection.List<UserDto>> getAll() {
        return Try.of(() -> {
            final java.util.List<User> users = userRepository.findAll();

            return List.ofAll(users)
                    .map(userMapper::mapToUserDto);
        });
    }

    @Override
    public Try<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest) {
        return Try.of(() -> {
            final User user = userBaseService.findUserById(updateUserRequest.id());

            Optional.ofNullable(updateUserRequest.name()).ifPresent(user::setName);
            Optional.ofNullable(updateUserRequest.describe()).ifPresent(user::setDescribe);
            Optional.ofNullable(updateUserRequest.gender()).ifPresent(user::setGender);

            userBaseService.update(user);

            return new UpdateUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getDescribe(),
                    user.getGender()
            );
        });
    }

    @Override
    public Try<CryptoDto> generateCrypto() {
        return Try.of(()-> {
            final User user = User.builder()
                    .crypto(GenerateID.create())
                    .deleteAt(DateOperation.addHoursToDate(LocalDateTime.now(),24))
                    .build();

            userBaseService.update(user);

            return new CryptoDto(user.getCrypto());
        });
    }

    @Override
    public Try<ValidateUserResponse> validateUser(CryptoDto crypto) {
        return Try.of(()-> {
            final User user = userRepository.findUserByCrypto(crypto.crypto());

            return new ValidateUserResponse(user.getId(),jwt.generateToken(crypto.crypto()),user.getPhotoId());
        });
    }

    public void uploadImageOnServ(MultipartFile file, Long userId) {
        try {
            String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/km-server/src/main/resources/static/images";
            filename = "azx" + userId + ".jpeg";
            final User user = userRepository.findById(userId).orElse(null);
            if(user != null) {
                user.setPhotoId(filename);
                userRepository.save(user);
                uploadService.storeFile(file, filename, uploadDir);
            }
        } catch(IOException ignored){}
    }

    public Resource loadFile(String filename) throws FileNotFoundException {
            final String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/km-server/src/main/resources/static/images";
            return uploadService.loadFile(filename, uploadDir);
    }
}
