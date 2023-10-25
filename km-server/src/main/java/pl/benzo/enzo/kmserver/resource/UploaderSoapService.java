package pl.benzo.enzo.kmserver.resource;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class UploaderSoapService {

    private final String soapServiceUrl = "http://localhost:8083/ws/uploads.wsdl";

}
