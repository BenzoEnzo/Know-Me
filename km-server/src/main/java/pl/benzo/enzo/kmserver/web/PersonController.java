package pl.benzo.enzo.kmserver.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmservicedto.profile.*;


import java.util.List;


@RestController
@RequestMapping("/api/public/person")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class PersonController {
    private final ProfileRestTemplate profileRestTemplate;
    @GetMapping(value = "/join")
    public ResponseEntity<?> join(){
        return profileRestTemplate.signUp();
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validate(@RequestBody SendCrypto sendCrypto){
        return profileRestTemplate.signIn(sendCrypto);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest) {
        return profileRestTemplate.updateUser(updateUserRequest);
    }

    @PostMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> read(@RequestBody ReadUserRequest readUserRequest) {
        return profileRestTemplate.readUser(readUserRequest);
    }

    @GetMapping(value = "/query-keys")
    public ResponseEntity<?> getKeys() {
        return profileRestTemplate.getKeys();
    }

    @PostMapping(value = "/create-key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createKey(@RequestBody String name) {
        return profileRestTemplate.createKey(name);
    }

    @PostMapping(value = "/create-area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArea(@RequestBody CreateAreaRequest createAreaRequest) {
        return profileRestTemplate.createArea(createAreaRequest);
    }

    @PostMapping(value = "/chat-queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> joinToQueue(@RequestBody AreaUserDto areaUserDto) {
        return profileRestTemplate.joinToQueue(areaUserDto);
    }
}
