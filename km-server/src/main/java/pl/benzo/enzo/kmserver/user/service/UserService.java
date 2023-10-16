package pl.benzo.enzo.kmserver.user.service;


import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.UserApi;
import pl.benzo.enzo.kmserver.user.model.*;
import pl.benzo.enzo.kmserver.user.model.dto.*;
import pl.benzo.enzo.kmserver.util.DateOperation;
import pl.benzo.enzo.kmserver.util.GenerateID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserApi {

    private final UserRepository userRepository;
    private final UserBaseService userBaseService;
    private final Jwt jwt;


    public Try<Optional<ReadUserResponse>> readUser(ReadUserRequest readUserRequest){
        return Try.of(()-> userRepository.findById(readUserRequest.id())
                 .map(User::getName)
                 .map(ReadUserResponse::new));
    }
    @Override
    public Try<List<UserDto>> getAll() {
        return Try.of(userBaseService::readAll);
    }

    @Override
    public Try<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest) {
        return Try.of(()-> {
                final User user = User.builder()
                        .name(updateUserRequest.name()).build();
                userBaseService.update(user);
            return new UpdateUserResponse(user.getCrypto());
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
            return new ValidateUserResponse(user.getId(),jwt.generateToken(crypto.crypto()));
        });
    }
}
