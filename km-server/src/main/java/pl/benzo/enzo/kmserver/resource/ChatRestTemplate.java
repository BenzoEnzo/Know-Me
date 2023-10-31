package pl.benzo.enzo.kmserver.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.benzo.enzo.kmserver.web.dto.MainSession;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

@Service
public class ChatRestTemplate {

    private final RestTemplate restTemplate;
    private final String CHAT_API;
    public ChatRestTemplate(RestTemplate restTemplate, External external){
        this.restTemplate = restTemplate;
        this.CHAT_API = external.getRestChat();
    }

    public void createSession(ChatSession session) {
        restTemplate.postForObject(CHAT_API + "/session/create", session,ChatSession.class);
    }

    public ResponseEntity<?> deleteSession(ChatSession session) {
        ChatSession response = restTemplate.postForObject(CHAT_API + "/session/delete", session, ChatSession.class);
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> validateSession(MainSession mainSession) {
        MainSession response = restTemplate.postForObject(CHAT_API + "/session/validate", mainSession, MainSession.class);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(response);
    }
}
