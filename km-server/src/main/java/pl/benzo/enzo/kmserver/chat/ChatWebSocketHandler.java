package pl.benzo.enzo.kmserver.chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> sessionGroups = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = String.valueOf(session.getAttributes().get("sessionId"));
        sessionGroups
                .computeIfAbsent(sessionId, k -> new ArrayList<>())
                .add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionId = String.valueOf(session.getAttributes().get("sessionId"));
        List<WebSocketSession> sessions = sessionGroups.get(sessionId);
        if (sessions != null) {
            for (WebSocketSession webSocketSession : sessions) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = String.valueOf(session.getAttributes().get("sessionId"));
        List<WebSocketSession> sessions = sessionGroups.get(sessionId);
        if (sessions != null) {
            sessions.remove(session);
        }
    }
}
