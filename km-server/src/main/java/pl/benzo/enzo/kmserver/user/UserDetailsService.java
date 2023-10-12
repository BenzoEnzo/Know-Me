package pl.benzo.enzo.kmserver.user;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.UploadImage;
import pl.benzo.enzo.kmserver.user.model.UserRepository;
import pl.benzo.enzo.kmuploader.image.UploadService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UploadService uploadService = new UploadService();
    private String filename;

    public void uploadImageOnServ(MultipartFile file) throws IOException {
        String uploadDir = "/home/devk/pobrane/";
        uploadService.storeFile(file, filename, uploadDir);
    }

    public Resource loadFile(UploadImage uploadImage) throws FileNotFoundException {
        this.filename = uploadImage.filename();
        return uploadService.loadFile(filename,uploadImage.uploadDirectory());
    }

}
