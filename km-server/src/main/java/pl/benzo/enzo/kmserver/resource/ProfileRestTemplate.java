package pl.benzo.enzo.kmserver.resource;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmservicedto.profile.AreaSizeDto;
import pl.benzo.enzo.kmserver.web.dto.MainSession;
import pl.benzo.enzo.kmservicedto.profile.*;


import java.util.List;
import java.util.Set;

@Service
public class ProfileRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    private final KafkaLogService kafkaLogService;
    public ProfileRestTemplate(RestTemplate restTemplate, External external, KafkaLogService kafkaLogService){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getRestProfile();
        this.kafkaLogService = kafkaLogService;
    }

    public ResponseEntity<?> signUp() {
       final Object communication = restTemplate.getForObject(SERVICE_API + "/account/sign-up", SendCrypto.class);
       return ResponseEntity.ok().body(communication);
    }

    public ResponseEntity<?> signIn(SendCrypto sendCrypto) {
        ResponseEntity<ValidateCrypto> response = restTemplate.postForEntity(SERVICE_API + "/account/sign-in", sendCrypto, ValidateCrypto.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getHeaders().containsKey("authorization")) {
            return ResponseEntity.ok().headers(response.getHeaders()).body(response.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    public ResponseEntity<?> updateUser(UpdateUserRequest updateUserRequest) {
        ResponseEntity<UpdateUserResponse> response = restTemplate.postForEntity(SERVICE_API + "/user/update", updateUserRequest, UpdateUserResponse.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity<?> readUser(ReadUserRequest readUserRequest) {
        ReadUserResponse response = restTemplate.postForObject(SERVICE_API + "/user/read", readUserRequest, ReadUserResponse.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> getKeys() {
        final ResponseEntity<?> response = restTemplate.getForEntity(SERVICE_API + "/key/query",List.of(KeyDto.class).getClass());
        return ResponseEntity.ok().body(response.getBody());
    }

    public ResponseEntity<?> createKey(String name) {
        Boolean result = restTemplate.postForObject(SERVICE_API + "/key/create", name, Boolean.class);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<?> createArea(CreateAreaRequest createAreaRequest) {
        Set<?> areas = restTemplate.postForObject(SERVICE_API + "/area/create", createAreaRequest, Set.class);
        return new ResponseEntity<>(areas, HttpStatus.CREATED);
    }

    public ResponseEntity<?> joinToQueue(AreaUserDto areaUserDto) {
        QueueJoinDto queue = restTemplate.postForObject(SERVICE_API + "/area/queue", areaUserDto, QueueJoinDto.class);
        return ResponseEntity.ok(queue);
    }

    public List<?> queryAreas(){
        return restTemplate.getForObject(SERVICE_API + "/area/query-areas", List.class);
    }

    public MainSession getPairsFromQueue() {

        return restTemplate.getForObject(SERVICE_API + "/admin/queue", MainSession.class);
    }

    public ResponseEntity<?> postSizeArea(AreaJoinDto areaJoinDto) {
        return ResponseEntity.ok().body(restTemplate.postForObject(SERVICE_API + "/area/area-people", areaJoinDto, AreaSizeDto.class));
    }

    public void sendInfoSessionRoom(AreaUserDto areaUserDto) {
        restTemplate.postForObject(SERVICE_API + "/area/on-conversation", areaUserDto, AreaUserDto.class);
    }

    public void deleteArea(Long id){
        restTemplate.delete(SERVICE_API + "/area/delete-area/{id}", id);
    }
}
