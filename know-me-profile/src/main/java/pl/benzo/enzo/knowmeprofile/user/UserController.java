package pl.benzo.enzo.knowmeprofile.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private final UserFacadeApi userFacadeApi;

    public UserController(UserFacadeApi userFacadeApi) {
        this.userFacadeApi = userFacadeApi;
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<?> queryAllUsers(){
        return ResponseEntity.ok(userFacadeApi.findAllUsers());
    }

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody SendCrypto sendCrypto){
        userFacadeApi.createAccount(sendCrypto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody SendCrypto sendCrypto){
        return ResponseEntity.ok(userFacadeApi.validateAccount(sendCrypto));
    }

}
