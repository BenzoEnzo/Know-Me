package pl.benzo.enzo.knowmeprofile.user.implementation.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.implementation.authentication.JwtToken;
import pl.benzo.enzo.kmservicedto.profile.SendCrypto;
import pl.benzo.enzo.kmservicedto.profile.ValidateCrypto;

@RestController
@Slf4j
@RequestMapping("/api/account")
public class AccountController {
    private final ProfileFacadeApi profileFacadeApi;
    private final JwtToken jwtToken;


    public AccountController(ProfileFacadeApi profileFacadeApi, JwtToken jwtToken) {
        this.profileFacadeApi = profileFacadeApi;
        this.jwtToken = jwtToken;

    }

    @GetMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(){
        SendCrypto sendCrypto = profileFacadeApi.generateCrypto();
        return ResponseEntity.ok()
                .body(sendCrypto);
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody SendCrypto sendCrypto){
        final ValidateCrypto validateCrypto = profileFacadeApi.validateAccount(sendCrypto);
        if(validateCrypto != null){
            String token = jwtToken.generateToken(sendCrypto.crypto());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " + token);
            return new ResponseEntity<>(validateCrypto, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    }


