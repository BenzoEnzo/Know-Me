package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.UserFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ReadUserRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ReadUserResponse;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.UpdateUserRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.UpdateUserResponse;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private final UserFacadeApi userFacadeApi;

    public UserController(UserFacadeApi userFacadeApi) {
        this.userFacadeApi = userFacadeApi;
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest) {
        final UpdateUserResponse updateUserResponse = userFacadeApi.updateUser(updateUserRequest);
        return ResponseEntity.ok()
                .body(updateUserResponse);
    }

    @PostMapping(value = "/read",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> read(@RequestBody ReadUserRequest readUserRequest) {
        final ReadUserResponse readUserResponse = userFacadeApi.readUser(readUserRequest);
        return ResponseEntity.ok()
                .body(readUserResponse);
    }



}
