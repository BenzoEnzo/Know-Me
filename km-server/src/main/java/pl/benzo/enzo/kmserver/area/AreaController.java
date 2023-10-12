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

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CreateAreaRequest createAreaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(areaService.createArea(createAreaRequest));
    }

}
