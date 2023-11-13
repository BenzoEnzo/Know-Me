package pl.benzo.enzo.kmserver.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploaderRestTemplate {
    private final RestTemplate restTemplate;
    private final String CHAT_API;
    public UploaderRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.CHAT_API = external.getSoapUploader();
    }


    public String uploadImage(MultipartFile file, String photoId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(convert(file)));
            body.add("photoId", photoId);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(CHAT_API + "/upload", requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public byte[] loadImage(String filename) {
        try {
            ResponseEntity<byte[]> response = restTemplate.getForEntity(CHAT_API + "/load/" + filename, byte[].class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // Metoda pomocnicza do konwersji MultipartFile na File
    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }
}

