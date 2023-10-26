package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {
    private final ProfileFacadeApi profileFacadeApi;

    public AdminController(ProfileFacadeApi profileFacadeApi) {
        this.profileFacadeApi = profileFacadeApi;

    }

    @GetMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<?> queryAllUsers(){
        return ResponseEntity.ok()
                .body(profileFacadeApi.findAllUsers());
    }

    @GetMapping(value = "/queue")
    @ResponseBody
    public ResponseEntity<?> getRandomPairs(){
        return ResponseEntity.ok()
                .body(profileFacadeApi.getRandomPairs());
    }

    }


