package pl.benzo.enzo.knowmesocket.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;
import pl.benzo.enzo.knowmesocket.chat.redis.ChatSessionService;


@RestController
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatSessionService chatSessionService;

    @PostMapping("/session/create")
    public ResponseEntity<ChatSession> createSession(@RequestBody ChatSession session) {
        return ResponseEntity.ok().body(chatSessionService.createSession(session));
    }

    @PostMapping("/session/delete")
    public ResponseEntity<?> deleteSession(@RequestBody ChatSession session) {
        chatSessionService.endSession(session.getSessionId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/session/validate")
    public ResponseEntity<?> validateSession(@RequestBody ChatSession session){
        return ResponseEntity.ok().body(chatSessionService.findSessionByTalkerId(session.getTalkerId1()));
    }

}
