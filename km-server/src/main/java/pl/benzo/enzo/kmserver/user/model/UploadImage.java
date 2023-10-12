package pl.benzo.enzo.kmserver.user.model;

import org.springframework.web.multipart.MultipartFile;

public record UploadImage(MultipartFile file, String filename, String uploadDirectory) {
}
