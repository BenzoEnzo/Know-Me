package pl.benzo.enzo.knowmeuploader.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.knowmeuploader.implementation.service.ImplUploadService;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/uploads")
public class UploadRestController {
    private final ImplUploadService implUploadService;

    @Autowired
    public UploadRestController(ImplUploadService implUploadService) {
        this.implUploadService = implUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("photoId") String photoId) {
        try {
            implUploadService.uploadImageOnServ(file, photoId);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @GetMapping("/load/{filename}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String filename) throws FileNotFoundException {
        Resource fileResource = implUploadService.loadFile(filename);
        try {
            byte[] fileBytes = StreamUtils.copyToByteArray(fileResource.getInputStream());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileBytes);
        } catch (IOException ignored) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
