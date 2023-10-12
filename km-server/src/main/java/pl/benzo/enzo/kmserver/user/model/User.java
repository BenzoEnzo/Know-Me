package pl.benzo.enzo.kmserver.user.model;


import jakarta.persistence.*;
import lombok.*;
import pl.benzo.enzo.kmserver.area.Area;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String crypto;
    private String describe;
    private LocalDateTime deleteAt;
    private Gender gender;
    public User(String name, String crypto, LocalDateTime deleteAt) {
        this.name = name;
        this.crypto = crypto;
        this.deleteAt = deleteAt;
    }

    public User() {

    }
}
