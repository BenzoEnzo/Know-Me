package pl.benzo.enzo.knowmeuploader.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class UploadController {
    private final ImplUploadService implUploadService;
    @PostMapping(value = "/profile-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("photoId") String photoId) throws IOException {
        implUploadService.uploadImageOnServ(file,photoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping(value = "/profile-image/load/{fileName}")
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String fileName) throws FileNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(implUploadService.loadFile(fileName));
    }
}
