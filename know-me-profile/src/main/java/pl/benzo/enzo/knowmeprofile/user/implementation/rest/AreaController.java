package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.profile.CreateAreaRequest;

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

    @PutMapping(value = "/on-conversation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> onConversation(@RequestBody AreaUserDto areaUserDto) {
        profileFacadeApi.refreshAreaState(areaUserDto);
        return ResponseEntity
                .ok().build();
    }

    @GetMapping(value = "/query-areas")
    public ResponseEntity<?> queryAreas(){
        return ResponseEntity.ok().body(profileFacadeApi.getAllAreas());
    }
}
