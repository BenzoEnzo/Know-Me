package pl.benzo.enzo.kmserver.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
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
    private final UploaderSoapService uploaderSoapService;
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
    public ResponseEntity<?> postSizeArea(@RequestBody Long keyId) {
        return profileRestTemplate.postSizeArea(keyId);
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


    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image,
                                         @RequestParam("photoId") String photoId) {
        try {
            UploadImageResponseImpl response = uploaderSoapService.uploadImageOnServer(image, photoId);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing the image.");
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Internal server error.");
        }
    }
}
