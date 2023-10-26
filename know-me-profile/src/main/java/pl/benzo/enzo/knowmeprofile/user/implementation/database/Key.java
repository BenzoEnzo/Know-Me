package pl.benzo.enzo.knowmeprofile.user.implementation.database;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "keys")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Key(String name){
        this.name = name;
    }
    public Key(Long id,String name){
        this.id = id;
        this.name = name;
    }
}
