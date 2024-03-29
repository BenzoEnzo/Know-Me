package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;
import pl.benzo.enzo.kmservicedto.profile.ReadUserRequest;
import pl.benzo.enzo.kmservicedto.profile.ReadUserResponse;
import pl.benzo.enzo.kmservicedto.profile.UpdateUserRequest;
import pl.benzo.enzo.kmservicedto.profile.UpdateUserResponse;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private final ProfileFacadeApi profileFacadeApi;

    public UserController(ProfileFacadeApi profileFacadeApi) {
        this.profileFacadeApi = profileFacadeApi;
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UpdateUserResponse> update(@RequestBody UpdateUserRequest updateUserRequest) {
        final UpdateUserResponse updateUserResponse = profileFacadeApi.updateUser(updateUserRequest);
        return ResponseEntity.ok()
                .body(updateUserResponse);
    }

    @PostMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ReadUserResponse> read(@RequestBody ReadUserRequest readUserRequest) {
        final ReadUserResponse readUserResponse = profileFacadeApi.readUser(readUserRequest);
        return ResponseEntity.ok()
                .body(readUserResponse);
    }



}
