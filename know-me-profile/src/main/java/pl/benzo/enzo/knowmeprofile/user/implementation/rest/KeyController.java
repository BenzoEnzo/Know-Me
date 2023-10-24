package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;

@RestController
@RequestMapping("/api/key")
public class KeyController {
    private final ProfileFacadeApi profileFacadeApi;

    public KeyController(ProfileFacadeApi profileFacadeApi) {
        this.profileFacadeApi = profileFacadeApi;
    }

    @GetMapping(value = "/query")
    public ResponseEntity<?> getKeys() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileFacadeApi.findAllKeys());
    }
    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody String name) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileFacadeApi.saveKey(name));
    }
}
