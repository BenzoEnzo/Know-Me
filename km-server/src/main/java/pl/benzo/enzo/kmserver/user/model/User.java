package pl.benzo.enzo.kmserver.user.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.benzo.enzo.kmserver.area.Area;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
public class User {
    @Id
    private Long id;
    private String name;
    private String crypto;
    private String describe;
    private LocalDateTime deleteAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Area> areas = new ArrayList<>();

    public User(String name, String crypto, LocalDateTime deleteAt) {
        this.name = name;
        this.crypto = crypto;
        this.deleteAt = deleteAt;
    }

    public User() {

    }
}
