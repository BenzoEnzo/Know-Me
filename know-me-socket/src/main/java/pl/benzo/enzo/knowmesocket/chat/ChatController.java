package pl.benzo.enzo.knowmesocket.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.benzo.enzo.knowmesocket.chat.redis.ChatSession;
import pl.benzo.enzo.knowmesocket.chat.redis.ChatSessionService;


@RestController
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatSessionService chatSessionService;

    @PostMapping("/session")
    public ResponseEntity<ChatSession> createSession(@RequestBody ChatSession session) {
        chatSessionService.createSession(session);
        return ResponseEntity.ok().body(session);
    }

    @PostMapping("/session/delete")
    public ResponseEntity<?> deleteSession(@RequestBody ChatSession session) {
        chatSessionService.endSession(session.getSessionId());
        return ResponseEntity.ok().build();
    }

}
