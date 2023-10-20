package pl.benzo.enzo.kmserver.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChattRepository extends JpaRepository<Chatt,Long> {
    Optional<Chatt> findByTalkerId1OrTalkerId2(Long talkerId1, Long talkerId2);
}
