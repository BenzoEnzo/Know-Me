package pl.benzo.enzo.kmuploader.image;



import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadService {

    @Value("{$file.upload.directory)")
    private String uploadDirectory;

    public void storeFile(MultipartFile file, String filename) throws IOException {
        Path filePath = Paths.get(uploadDirectory + File.separator + filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public Resource loadFile(String filename) throws FileNotFoundException {
        Path filePath = Paths.get(uploadDirectory + File.separator + filename);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (MalformedURLException | FileNotFoundException ex) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }
}
