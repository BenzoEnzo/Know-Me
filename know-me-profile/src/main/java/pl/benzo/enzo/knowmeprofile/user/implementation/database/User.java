package pl.benzo.enzo.knowmeprofile.user.implementation.database;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.Gender;

import java.time.LocalDateTime;

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
    private String photoId;
    public User(){}
}
