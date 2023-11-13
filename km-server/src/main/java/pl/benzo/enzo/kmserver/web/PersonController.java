package pl.benzo.enzo.kmserver.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmserver.resource.UploaderRestTemplate;
import pl.benzo.enzo.kmserver.resource.UploaderSoapService;
import pl.benzo.enzo.kmserver.web.dto.MainSession;
import pl.benzo.enzo.kmserver.web.dto.UploadImageResponseImpl;
import pl.benzo.enzo.kmservicedto.profile.*;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;


import java.io.IOException;


@RestController
@RequestMapping("/api/public/person")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class PersonController {
    private final ProfileRestTemplate profileRestTemplate;
//    private final UploaderSoapService uploaderSoapService;
    private final UploaderRestTemplate uploaderRestTemplate;
    private final ChatRestTemplate chatRestTemplate;

    @GetMapping(value = "/join")
    public ResponseEntity<?> join() {
        return profileRestTemplate.signUp();
    }

    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validate(@RequestBody SendCrypto sendCrypto) {
        return profileRestTemplate.signIn(sendCrypto);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest) {
        return profileRestTemplate.updateUser(updateUserRequest);
    }

    @PostMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> read(@RequestBody ReadUserRequest readUserRequest) {
        return profileRestTemplate.readUser(readUserRequest);
    }

    @GetMapping(value = "/query-keys")
    public ResponseEntity<?> getKeys() {
        return profileRestTemplate.getKeys();
    }

    @PostMapping(value = "/create-key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createKey(@RequestBody String name) {
        return profileRestTemplate.createKey(name);
    }

    @PostMapping(value = "/create-area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArea(@RequestBody CreateAreaRequest createAreaRequest) {
        return profileRestTemplate.createArea(createAreaRequest);
    }

    @PostMapping(value = "/chat-queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> joinToQueue(@RequestBody AreaUserDto areaUserDto) {
        return profileRestTemplate.joinToQueue(areaUserDto);
    }

    @PostMapping(value = "/area-people", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postSizeArea(@RequestBody AreaJoinDto areaJoinDto) {
        return profileRestTemplate.postSizeArea(areaJoinDto);
    }
    @PostMapping(value = "/go-talk", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> goTalk(@RequestBody MainSession mainSession) {
            ResponseEntity<?> response = chatRestTemplate.validateSession(mainSession);
            AreaUserDto areaUserDto = new AreaUserDto(mainSession.talkerId1(), mainSession.talkerId1(), null, null, true, true, false);
            if(response.hasBody() && response.getStatusCode().is2xxSuccessful()) {
                profileRestTemplate.sendInfoSessionRoom(areaUserDto);
            }
            return response;
    }


    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("photoId") String photoId) {
        String response = uploaderRestTemplate.uploadImage(file, photoId);
        if ("SUCCESS".equals(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/load/{filename}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String filename) {
        byte[] fileBytes = uploaderRestTemplate.loadImage(filename);
        if (fileBytes != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileBytes);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

