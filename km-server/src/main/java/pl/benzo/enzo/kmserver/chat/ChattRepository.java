package pl.benzo.enzo.kmserver.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChattRepository extends JpaRepository<Chatt,Long> {
}
