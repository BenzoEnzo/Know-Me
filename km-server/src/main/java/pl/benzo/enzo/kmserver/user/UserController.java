package pl.benzo.enzo.kmserver.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.dto.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserApi userApi;


    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> getUsers() {
       final List<User> usersResponse = userApi.getAll();
       return ResponseEntity.ok(usersResponse);
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UpdateUserResponse> update(@RequestBody UpdateUserRequest updateUserRequest) {
        final UpdateUserResponse updateUserResponse = userApi.updateUser(updateUserRequest)
                .onSuccess(result -> log.info("Successful {}", updateUserRequest.id()))
                .onFailure(throwable -> log.error("Error while update user with id: {}", updateUserRequest.id()))
                .getOrElseThrow(()-> new IllegalArgumentException("Error during update"));
        return ResponseEntity.ok()
                .body(updateUserResponse);
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidateUserResponse> validate(@RequestBody CryptoDto cryptoDto) {
        final ValidateUserResponse validateUserResponse = userApi.validateUser(cryptoDto)
                .onSuccess(result -> log.info("Successful"))
                .onFailure(throwable -> log.error("Error while validate", throwable))
                .getOrElseThrow(()-> new BadCredentialsException("Invalid credentials"));
        return ResponseEntity.ok()
                .body(validateUserResponse);
    }

    @PostMapping(value = "/read",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ReadUserResponse>readUser(@RequestBody ReadUserRequest readUserRequest) {
        final ReadUserResponse readUserResponse = userApi.readUser(readUserRequest)
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

    @PostMapping(value = "/profile-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        userApi.uploadImageOnServ(file,userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping(value = "/profile-image/load/{fileName}")
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String fileName) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userApi.loadFile(fileName));
    }
}
