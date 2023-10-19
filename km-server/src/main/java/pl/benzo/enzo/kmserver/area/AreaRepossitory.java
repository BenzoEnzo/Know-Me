package pl.benzo.enzo.kmserver.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.kmserver.user.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface AreaRepossitory extends JpaRepository<Area,Long> {
    Area findAreaBySessionId(String sessionId);
    void deleteAreaBySessionId(String sessionId);
    Area findAreaByUserId(Long id);
    List<Area> findAllByIsInQueueAndKey_Id(boolean isInQueue,Long keyId);
    Set<Area> findAllByKey_Id(Long id);
}
