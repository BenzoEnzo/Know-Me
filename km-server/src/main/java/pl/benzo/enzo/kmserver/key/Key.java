package pl.benzo.enzo.kmserver.key;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import pl.benzo.enzo.kmserver.area.Area;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Key {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "key", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Area> areas = new ArrayList<>();
}
