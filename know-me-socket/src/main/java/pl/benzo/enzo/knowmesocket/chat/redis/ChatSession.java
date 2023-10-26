package pl.benzo.enzo.knowmesocket.chat.redis;


import lombok.*;



@Builder
@Data
public class ChatSession {
    private String sessionId;
    private Long talkerId1;
    private Long talkerId2;
}
