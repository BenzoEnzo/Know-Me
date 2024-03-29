package pl.benzo.enzo.knowmeprofile.user.implementation.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long> {
    Area findAreaByUser_Id(Long id);
    void deleteAreaBySessionId(String sessionId);
    void deleteAreaByUser_Id(Long id);
    Optional<Area> findByUser_Id(Long id);
    List<Area> findAllByIsInQueueAndDuringConversation(boolean isInQueue,boolean duringConversation);
    Set<Area> findAllByKey_Id(Long id);
}
