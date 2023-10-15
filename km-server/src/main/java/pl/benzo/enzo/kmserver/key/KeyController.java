package pl.benzo.enzo.kmserver.key;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.token.Jwt;

@RestController
@RequestMapping("/api/key")
@CrossOrigin(origins = "http://localhost:3000")
public class KeyController {
    private final KeyService keyService;

    public KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping()
    public ResponseEntity<?> getKeys() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(keyService.getAll());
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody String name) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(keyService.saveKey(name));
    }
}
