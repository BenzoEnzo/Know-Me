package pl.benzo.enzo.kmserver.area;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmserver.area.dto.AreaJoinDto;
import pl.benzo.enzo.kmserver.area.dto.AreaUserDto;
import pl.benzo.enzo.kmserver.area.dto.QueueJoinDto;

@RestController
@RequestMapping("/api/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;
    private final QueueService queueService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateAreaRequest createAreaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(areaService.createArea(createAreaRequest));
    }

    @PostMapping(value = "/queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> joinToQueue(@RequestBody AreaUserDto areaUserDto) {
        return ResponseEntity
                .ok().body(queueService.addUserToQueue(areaUserDto));
    }
}
