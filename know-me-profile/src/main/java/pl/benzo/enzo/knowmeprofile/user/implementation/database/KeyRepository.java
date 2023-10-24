package pl.benzo.enzo.knowmeprofile.user.implementation.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key,Long> {
    Key findKeyByName(String name);
}
