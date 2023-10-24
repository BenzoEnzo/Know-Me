package pl.benzo.enzo.knowmeuploader.implementation;

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
    private String filename;
    public void uploadImageOnServ(MultipartFile file, String photoId) {
        try {
            String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/km-server/src/main/resources/static/images";
            filename = "azx" + photoId + ".jpeg";
            uploadService.storeFile(file, filename, uploadDir);
        } catch(IOException ignored){}
    }

    public Resource loadFile(String filename) throws FileNotFoundException {
        final String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/km-server/src/main/resources/static/images";
        return uploadService.loadFile(filename, uploadDir);
    }
}
