package pl.benzo.enzo.knowmesocket.chat.redis;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private static final String SESSION_PREFIX = "session:";


    private final RedisTemplate<String, Object> redisTemplate;

    public void createSession(ChatSession session) {
        redisTemplate.opsForHash().put(SESSION_PREFIX + session.getSessionId(), "talkerId1", session.getTalkerId1());
        redisTemplate.opsForHash().put(SESSION_PREFIX + session.getSessionId(), "talkerId2", session.getTalkerId2());
    }

    public ChatSession getSession(String sessionId) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(SESSION_PREFIX + sessionId);

        ChatSession session = new ChatSession();
        session.setSessionId(sessionId);
        session.setTalkerId1((Long) entries.get("talkerId1"));
        session.setTalkerId2((Long) entries.get("talkerId2"));

        return session;
    }


}

