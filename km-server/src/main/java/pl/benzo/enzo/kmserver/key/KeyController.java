package pl.benzo.enzo.kmserver.key;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key")
@RequiredArgsConstructor
public class KeyController {
    private final KeyService keyService;

    @GetMapping()
    public ResponseEntity<?> getKeys() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(keyService.getAll());
    }
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody String name) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(keyService.saveKey(name));
    }
}
