package pl.benzo.enzo.kmserver.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.PhotoRequest;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) {
        return uploadBaseController.uploadProfilePicture(file, "7");
    }
    @GetMapping
    public ResponseEntity<?> getProfilePicture(@RequestBody PhotoRequest photoRequest) throws FileNotFoundException {
       return uploadBaseController.getProfilePicture(photoRequest.crypto());
    }
}
