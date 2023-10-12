package pl.benzo.enzo.kmserver.user.model;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmuploader.image.UploadService;

@Service
@RequiredArgsConstructor
public class PhotoUploader {
    private UploadService uploadService;

}
