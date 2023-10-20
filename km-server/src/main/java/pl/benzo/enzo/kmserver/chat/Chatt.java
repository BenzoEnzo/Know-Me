package pl.benzo.enzo.kmserver.chat;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.benzo.enzo.kmserver.user.model.User;

@Entity
@Table(name = "Chatts")
@CrossOrigin(origins = "http://localhost:3000")
@Builder
@Data
@AllArgsConstructor
public class Chatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long talkerId1;
    private Long talkerId2;
    private boolean talker1Accepted;
    private boolean talker2Accepted;
    private boolean inConversation;

    public Chatt() {

    }
}
