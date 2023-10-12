package pl.benzo.enzo.kmserver.area;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @GetMapping()
    public ResponseEntity<?> getAreas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(areaService.getAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateAreaRequest createAreaRequest) {
        areaService.createArea(createAreaRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
