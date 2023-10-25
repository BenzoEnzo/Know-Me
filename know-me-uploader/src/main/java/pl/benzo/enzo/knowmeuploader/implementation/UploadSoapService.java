package pl.benzo.enzo.knowmeuploader.implementation;


import jakarta.activation.DataHandler;
import jakarta.xml.ws.soap.MTOM;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.benzo.enzo.kmservicedto.uploader.UploadImageResponse;
import pl.benzo.enzo.kmservicedto.uploader.UploadImageRequest;
import pl.benzo.enzo.kmservicedto.uploader.LoadImageRequest;
import pl.benzo.enzo.kmservicedto.uploader.LoadImageResponse;
import pl.benzo.enzo.knowmeuploader.implementation.service.DataHandlerImageService;
import pl.benzo.enzo.knowmeuploader.implementation.service.ImplUploadService;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;


@Endpoint
@MTOM
public class UploadSoapService {
    private static final String NAMESPACE_URI = "http://know-me-beta.pl/uploads";
    private final ImplUploadService implUploadService;


    public UploadSoapService(ImplUploadService implUploadService) {
        this.implUploadService = implUploadService;
    }



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "uploadImageRequest")
    @ResponsePayload
    public UploadImageResponse uploadImage(@RequestPayload UploadImageRequest request) {
        UploadImageResponse response = new UploadImageResponse();

        try {
            DataHandler dataHandler = request.getFile();
            MultipartFile multipartFile = new DataHandlerImageService(dataHandler, request.getPhotoId() + ".jpeg");
            implUploadService.uploadImageOnServ(multipartFile, request.getPhotoId());
            response.setStatus("SUCCESS");
        } catch (Exception e) {
            response.setStatus("ERROR");
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loadImageRequest")
    @ResponsePayload
    public LoadImageResponse loadImage(@RequestPayload LoadImageRequest request) throws FileNotFoundException {
        LoadImageResponse response = new LoadImageResponse();

        Resource fileResource = implUploadService.loadFile(request.getFileName());

        try {
            byte[] fileBytes = StreamUtils.copyToByteArray(fileResource.getInputStream());
            String fileBase64 = Base64.getEncoder().encodeToString(fileBytes);
            response.setFileBase64(fileBase64);
        } catch (IOException ignored) {
        }

        return response;
    }
}
