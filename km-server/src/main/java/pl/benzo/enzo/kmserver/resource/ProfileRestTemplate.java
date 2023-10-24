package pl.benzo.enzo.kmserver.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmserver.web.dto.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProfileRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    public ProfileRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getProfile();
    }

    public ResponseEntity<?> signUp() {
       final Object communication = restTemplate.getForObject(SERVICE_API + "/account/sign-up", SendCrypto.class);
       return ResponseEntity.ok().body(communication);
    }

    public ResponseEntity<?> signIn(SendCrypto sendCrypto) {
        ResponseEntity<ValidateCrypto> response = restTemplate.postForEntity(SERVICE_API + "/account/sign-in", sendCrypto, ValidateCrypto.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getHeaders().containsKey("Authorization")) {
            String token = Objects.requireNonNull(response.getHeaders().get("Authorization")).get(0);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    public ResponseEntity<?> updateUser(UpdateUserRequest updateUserRequest) {
        UpdateUserResponse response = restTemplate.postForObject(SERVICE_API + "/api/user/update", updateUserRequest, UpdateUserResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> readUser(ReadUserRequest readUserRequest) {
        ReadUserResponse response = restTemplate.postForObject(SERVICE_API + "/api/user/read", readUserRequest, ReadUserResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getKeys() {
        List<?> keys = restTemplate.getForObject(SERVICE_API + "/api/key/query", List.class);
        return ResponseEntity.ok(keys);
    }

    public ResponseEntity<?> createKey(String name) {
        Boolean result = restTemplate.postForObject(SERVICE_API + "/api/key/create", name, Boolean.class);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<?> createArea(CreateAreaRequest createAreaRequest) {
        Set<?> areas = restTemplate.postForObject(SERVICE_API + "/api/area/create", createAreaRequest, Set.class);
        return new ResponseEntity<>(areas, HttpStatus.CREATED);
    }

    public ResponseEntity<?> joinToQueue(AreaUserDto areaUserDto) {
        QueueJoinDto queue = restTemplate.postForObject(SERVICE_API + "/api/area/queue", areaUserDto, QueueJoinDto.class);
        return ResponseEntity.ok(queue);
    }

}
