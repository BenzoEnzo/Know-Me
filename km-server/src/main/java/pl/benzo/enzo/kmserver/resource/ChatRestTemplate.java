package pl.benzo.enzo.kmserver.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    public ChatRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getSocket();
    }

}
