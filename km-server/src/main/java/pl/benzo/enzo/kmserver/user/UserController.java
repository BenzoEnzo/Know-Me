package pl.benzo.enzo.kmserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.key.KeyService;
import pl.benzo.enzo.kmserver.token.Jwt;
import pl.benzo.enzo.kmserver.user.model.CreateRequest;
import pl.benzo.enzo.kmserver.user.model.IdDto;
import pl.benzo.enzo.kmserver.user.model.User;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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
        userService.saveUser(createRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> validate(@RequestBody IdDto idDto) {
        try {
        final String crypto = idDto.crypto();
        final String token = jwt.generateToken(crypto);
        Object validationResult = userService.validateIdUser(idDto);
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generate() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.generateUser());
    }
}
