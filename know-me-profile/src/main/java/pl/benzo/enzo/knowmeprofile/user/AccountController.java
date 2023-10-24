package pl.benzo.enzo.knowmeprofile.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.authentication.JwtToken;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.dto.ValidateCrypto;

@RestController
@Slf4j
@RequestMapping("/api/account")
public class AccountController {
    private final UserFacadeApi userFacadeApi;
    private final JwtToken jwtToken;

    public AccountController(UserFacadeApi userFacadeApi, JwtToken jwtToken) {
        this.userFacadeApi = userFacadeApi;
        this.jwtToken = jwtToken;
    }

    @GetMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(){
        SendCrypto sendCrypto = userFacadeApi.generateCrypto();
        return ResponseEntity.ok()
                .body(sendCrypto);
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody SendCrypto sendCrypto){
        final ValidateCrypto validateCrypto = userFacadeApi.validateAccount(sendCrypto);
        if(validateCrypto != null){
            String token = jwtToken.generateToken(sendCrypto.crypto());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    }


