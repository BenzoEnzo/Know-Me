package pl.benzo.enzo.kmserver.web.dto;

import jakarta.activation.DataHandler;
import org.springframework.web.multipart.MultipartFile;

public record UploadImageRequestImpl(MultipartFile file, String photoId) {
}
