package pl.benzo.enzo.kmserver.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.dto.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserApi userApi;
    private final Jwt jwt;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {
       final List<UserDto> usersResponse = userApi.getAll()
               .onSuccess(result -> log.info(String.valueOf(result.size())))
               .onFailure(throwable -> log.error("Error while fetching users list", throwable))
               .getOrElseThrow(() -> new IllegalArgumentException("Error while fetching list"));
       return ResponseEntity.ok(usersResponse);
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UpdateUserResponse> update(@RequestBody UpdateUserRequest updateUserRequest) {
        final UpdateUserResponse updateUserResponse = userApi.updateUser(updateUserRequest)
                .onSuccess(result -> log.info("Successful {}", updateUserRequest.id()))
                .onFailure(throwable -> log.error("Error while update user with id: {}", updateUserRequest.id()))
                .getOrElseThrow(()-> new IllegalArgumentException("Error during update"));
        return ResponseEntity.ok(updateUserResponse);
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidateUserResponse> validate(@RequestBody CryptoDto cryptoDto) {
        final ValidateUserResponse validateUserResponse = userApi.validateUser(cryptoDto)
                .onSuccess(result -> log.info("Successful"))
                .onFailure(throwable -> log.error("Error while validate", throwable))
                .getOrElseThrow(()-> new IllegalArgumentException("Error during validate"));
        return ResponseEntity.ok(validateUserResponse);
    }

    @PostMapping(value = "/read",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Optional<ReadUserResponse>> readUser(@RequestBody ReadUserRequest readUserRequest) {
        final Optional<ReadUserResponse> readUserResponse = userApi.readUser(readUserRequest)
                .onSuccess(result -> log.info("Successful"))
                .onFailure(throwable -> log.error("Error while validate", throwable))
                .getOrElseThrow(()-> new IllegalArgumentException("Error during validate"));
        return ResponseEntity.ok(readUserResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CryptoDto> generate() {
        final CryptoDto getCryptoResponse = userApi.generateCrypto()
                .onSuccess(result -> log.info("Successful"))
                .onFailure(throwable -> log.error("Error while validate", throwable))
                .getOrElseThrow(()-> new IllegalArgumentException("Error during validate"));
        return ResponseEntity.ok(getCryptoResponse);
    }
}
