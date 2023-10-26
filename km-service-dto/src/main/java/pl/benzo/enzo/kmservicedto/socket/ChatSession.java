package pl.benzo.enzo.kmservicedto.socket;


import lombok.*;



@Builder
@Data
public class ChatSession {
    private String sessionId;
    private Long talkerId1;
    private Long talkerId2;
}
