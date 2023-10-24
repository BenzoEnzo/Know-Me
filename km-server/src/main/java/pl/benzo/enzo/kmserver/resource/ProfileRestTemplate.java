package pl.benzo.enzo.kmserver.resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmserver.web.dto.SendCrypto;

@Service
public class ProfileRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    public ProfileRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getProfile();
    }

    public SendCrypto signUp() {
        return restTemplate.getForObject(SERVICE_API + "/account/sign-up", SendCrypto.class);
    }

}
