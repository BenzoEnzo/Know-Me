package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.UserFacadeApi;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {
    private final UserFacadeApi userFacadeApi;

    public AdminController(UserFacadeApi userFacadeApi) {
        this.userFacadeApi = userFacadeApi;

    }

    @GetMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<?> queryAllUsers(){
        return ResponseEntity.ok()
                .body(userFacadeApi.findAllUsers());
    }

    }


