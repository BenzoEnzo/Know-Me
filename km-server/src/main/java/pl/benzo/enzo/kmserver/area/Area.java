package pl.benzo.enzo.kmserver.area;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.benzo.enzo.kmserver.key.Key;
import pl.benzo.enzo.kmserver.user.model.Gender;
import pl.benzo.enzo.kmserver.user.model.User;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "areas")
@CrossOrigin(origins = "http://localhost:3000")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "key_id")
    private Key key;

    private String sessionId;

    private boolean joined;
    private boolean isInQueue;
    private Gender partnerType;
    private boolean duringConversation;

    public Area(User user, Key key, String sessionId) {
        this.user = user;
        this.key = key;
        this.sessionId = sessionId;
    }

    public Area(Long id, User user, Key key, boolean isInQueue, Gender partnerType){
        this.id = id;
        this.user = user;
        this.key = key;
        this.isInQueue = isInQueue;
        this.partnerType = partnerType;
    }
}
