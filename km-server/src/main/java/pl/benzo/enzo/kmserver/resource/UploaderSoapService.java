package pl.benzo.enzo.kmserver.resource;

import com.sun.istack.ByteArrayDataSource;
import jakarta.activation.DataHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.client.core.WebServiceTemplate;
import pl.benzo.enzo.kmserver.web.dto.UploadImageResponseImpl;
import pl.benzo.enzo.kmservicedto.uploader.UploadImageRequest;
import pl.benzo.enzo.kmservicedto.uploader.UploadImageResponse;

import java.io.IOException;

@Service
public class UploaderSoapService {

    private final String soapServiceUrl = "http://localhost:8083/ws/uploads.wsdl";
    private final WebServiceTemplate webServiceTemplate;

    public UploaderSoapService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public UploadImageResponseImpl uploadImageOnServer(MultipartFile image, String photoId) throws IOException {

        DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(image.getBytes(), image.getContentType()));


        UploadImageRequest request = new UploadImageRequest();
        request.setFile(dataHandler);
        request.setPhotoId(photoId);


        UploadImageResponse response = (UploadImageResponse) webServiceTemplate.marshalSendAndReceive(soapServiceUrl, request);


        return new UploadImageResponseImpl(response.getStatus());
    }

}
