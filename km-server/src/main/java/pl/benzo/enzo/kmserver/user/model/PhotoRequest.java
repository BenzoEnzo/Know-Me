package pl.benzo.enzo.kmserver.user.model;
import org.springframework.web.multipart.MultipartFile;
public record PhotoRequest(MultipartFile multiPartFile, String crypto) {
}
