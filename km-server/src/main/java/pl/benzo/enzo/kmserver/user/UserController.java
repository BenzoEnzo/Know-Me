package pl.benzo.enzo.kmserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.key.KeyService;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.CreateRequest;
import pl.benzo.enzo.kmserver.user.model.IdDto;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Jwt jwt;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll());
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody CreateRequest createRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(createRequest));
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> validate(@RequestBody IdDto idDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION,jwt.generateToken(idDto.crypto()))
                .body(userService.validateIdUser(idDto));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generate() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.generateUser());
    }
}
