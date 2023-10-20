package pl.benzo.enzo.kmserver.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.area.CreateAreaRequest;
import pl.benzo.enzo.kmserver.area.dto.StartDtoRequest;

@RestController
@Slf4j
@RequestMapping("/api/chatt")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ChattController {
    private final ChattService chattService;

    @PostMapping(value = "/start",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> startChatting(@RequestBody StartDtoRequest startDtoRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chattService.getSession(startDtoRequest.id()));
    }

}
