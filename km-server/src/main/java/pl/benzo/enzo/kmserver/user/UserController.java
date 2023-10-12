package pl.benzo.enzo.kmserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.key.KeyService;
import pl.benzo.enzo.kmserver.user.model.CreateRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll());
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CreateRequest createRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(createRequest));
    }
}
