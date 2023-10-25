package pl.benzo.enzo.kmserver.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmserver.web.dto.*;

import java.util.List;
import java.util.Set;

@Service
public class ChatRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    public ChatRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getSocket();
    }

    public ResponseEntity<?> signUp() {
       final Object communication = restTemplate.getForObject(SERVICE_API + "/account/sign-up", SendCrypto.class);
       return ResponseEntity.ok().body(communication);
    }

}
