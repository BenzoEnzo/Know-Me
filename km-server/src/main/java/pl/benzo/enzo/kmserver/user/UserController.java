package pl.benzo.enzo.kmserver.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.dto.UpdateRequest;
import pl.benzo.enzo.kmserver.user.model.dto.CryptoDto;
import pl.benzo.enzo.kmserver.user.model.dto.UserDto;
import pl.benzo.enzo.kmserver.user.service.UserService;
import reactor.core.Exceptions;

import java.util.List;

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
               .getOrElseThrow(() -> new IllegalArgumentException("error"));
       return ResponseEntity.ok(usersResponse);
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody UpdateRequest updateRequest) {
        userService.saveUser(updateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> validate(@RequestBody CryptoDto cryptoDto) {
        try {
        final String crypto = cryptoDto.crypto();
        final String token = jwt.generateToken(crypto);
        Object validationResult = userService.validateIdUser(cryptoDto);
        return ResponseEntity
                .ok()
                .header("Authorization",token)
                .body(validationResult);
    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping(value = "/read",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> readUser(@RequestBody Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.readUser(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generate() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.generateUser());
    }
}
