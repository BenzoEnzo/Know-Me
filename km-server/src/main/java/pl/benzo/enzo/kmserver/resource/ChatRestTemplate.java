package pl.benzo.enzo.kmserver.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

@Service
public class ChatRestTemplate {

    private final RestTemplate restTemplate;
    private final String SERVICE_API;
    public ChatRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.SERVICE_API = external.getRestChat();
    }

    public void createSession(ChatSession session) {
        restTemplate.postForObject(SERVICE_API + "/chat/session/create", session,ChatSession.class);
    }

    public ResponseEntity<?> deleteSession(ChatSession session) {
        ChatSession response = restTemplate.postForObject(SERVICE_API + "/session/delete", session, ChatSession.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> validateSession(ChatSession session) {
        ChatSession response = restTemplate.postForObject(SERVICE_API + "/chat/session/validate", session, ChatSession.class);
        return ResponseEntity.ok().body(response);
    }
}
