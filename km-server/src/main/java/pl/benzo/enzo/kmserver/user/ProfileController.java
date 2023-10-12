package pl.benzo.enzo.kmserver.user;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.PhotoRequest;
import pl.benzo.enzo.kmuploader.image.UploadBaseController;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private UploadBaseController uploadBaseController;
    @PostMapping
    public ResponseEntity<?> uploadPhoto(@RequestBody PhotoRequest photoRequest) {
        return uploadBaseController.uploadProfilePicture(photoRequest.multiPartFile(), photoRequest.crypto());
    }
    @GetMapping
    public ResponseEntity<?> getProfilePicture(@RequestBody PhotoRequest photoRequest) throws FileNotFoundException {
       return uploadBaseController.getProfilePicture(photoRequest.crypto());
    }
}
