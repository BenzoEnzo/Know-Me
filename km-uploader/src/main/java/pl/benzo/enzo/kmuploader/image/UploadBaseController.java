package pl.benzo.enzo.kmuploader.image;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RequiredArgsConstructor
public class UploadBaseController {
    private final UploadService uploadService;
    public ResponseEntity<?> uploadProfilePicture(MultipartFile file, String id) {
        final String filename = "profile_" + id;
        try {
            uploadService.storeFile(file, filename);
            return ResponseEntity.ok().body("Profile picture uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store file");
        }
    }

    public ResponseEntity<?> getProfilePicture(String id) throws FileNotFoundException {
        String filename = "profile_" + id + ".jpg";
        Resource file = uploadService.loadFile(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }

}
