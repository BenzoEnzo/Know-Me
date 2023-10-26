package pl.benzo.enzo.knowmesocket.chat.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatSessionService {
    private static final Logger loggerChatSessionService = LoggerFactory.getLogger(ChatSessionService.class);

    private static final String SESSION_PREFIX = "session:";
    private static final String TALKER_INDEX = "talker_index";
    private final RedisTemplate<String, Object> redisTemplate;

    public ChatSession createSession(ChatSession session) {
        final String randomSession = String.valueOf(session.getTalkerId1() + session.getTalkerId2());

        if (redisTemplate.opsForHash().hasKey(TALKER_INDEX, session.getTalkerId1().toString()) ||
                redisTemplate.opsForHash().hasKey(TALKER_INDEX, session.getTalkerId2().toString())) {
            throw new IllegalStateException("Jeden z talkerów już ma aktywną sesję.");
        }

        redisTemplate.opsForHash().put(SESSION_PREFIX + randomSession, "talkerId1", session.getTalkerId1());
        redisTemplate.opsForHash().put(SESSION_PREFIX + randomSession, "talkerId2", session.getTalkerId2());

        loggerChatSessionService.info("Stworzono sesje: " + randomSession);

        redisTemplate.opsForHash().put(TALKER_INDEX, session.getTalkerId1().toString(), randomSession);
        redisTemplate.opsForHash().put(TALKER_INDEX, session.getTalkerId2().toString(), randomSession);

        return ChatSession.builder()
                .talkerId1(session.getTalkerId1())
                .talkerId2(session.getTalkerId2())
                .sessionId(randomSession).build();
    }

    public void endSession(String sessionId) {
        Long talkerId1 = (Long) redisTemplate.opsForHash().get(SESSION_PREFIX + sessionId, "talkerId1");
        Long talkerId2 = (Long) redisTemplate.opsForHash().get(SESSION_PREFIX + sessionId, "talkerId2");

        redisTemplate.opsForHash().delete("talkers_index", talkerId1.toString());
        redisTemplate.opsForHash().delete("talkers_index", talkerId2.toString());

        loggerChatSessionService.info("Zamknieto sesje uzytkownika: " + talkerId1 + " oraz" + talkerId2);
    }

    public Optional<ChatSession> findSessionByTalkerId(Long talkerId) {
        if (!redisTemplate.opsForHash().hasKey(TALKER_INDEX, talkerId.toString())) {
            return Optional.empty();
        }

        final String sessionId = (String) redisTemplate.opsForHash().get(TALKER_INDEX, talkerId.toString());
        return Optional.of(ChatSession.builder()
                .sessionId(sessionId)
                .build());
    }
}

