package pl.benzo.enzo.kmserver.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.kmserver.user.model.User;

@Repository
public interface AreaRepossitory extends JpaRepository<Area,Long> {

    Area findAreaBySessionId(String sessionId);
}
