package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.AreaUserDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.CreateAreaRequest;

@RestController
@RequestMapping("/api/area")
@RequiredArgsConstructor
public class AreaController {
private final ProfileFacadeApi profileFacadeApi;

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateAreaRequest createAreaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileFacadeApi.createArea(createAreaRequest));
    }

    @PostMapping(value = "/queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> joinToQueue(@RequestBody AreaUserDto areaUserDto) {
        return ResponseEntity
                .ok().body(profileFacadeApi.addUserToQueue(areaUserDto));
    }
}
