package pl.benzo.enzo.kmserver.core;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImplUploadService {
    private final UploadService uploadService = new UploadService();
    private final static String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/know-me-uploader/src/main/resources/static/photos";
    public void uploadImageOnServ(MultipartFile file, String photoId) {
        try {
            String filename = "azx" + photoId + ".jpeg";
            uploadService.storeFile(file, filename, uploadDir);
        } catch(IOException ignored){}
    }

    public Resource loadFile(String filename) throws FileNotFoundException {
        return uploadService.loadFile(filename, uploadDir);
    }
}
