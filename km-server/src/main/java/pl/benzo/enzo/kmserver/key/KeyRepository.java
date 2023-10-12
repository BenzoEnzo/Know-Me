package pl.benzo.enzo.kmserver.key;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key,Long> {
    Key findKeyByName(String name);
}
