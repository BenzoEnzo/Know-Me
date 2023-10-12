package pl.benzo.enzo.kmserver.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.UploadImage;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/user/details")
@RequiredArgsConstructor
public class UserDetailsController {
    private final UserDetailsService userDetailsService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        userDetailsService.uploadImageOnServ(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping
    public ResponseEntity<?> getProfilePicture(@RequestBody UploadImage uploadImage) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsService.loadFile(uploadImage));
    }
}
