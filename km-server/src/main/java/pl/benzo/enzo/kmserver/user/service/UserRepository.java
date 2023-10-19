package pl.benzo.enzo.kmserver.user.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.kmserver.user.model.User;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void deleteAllByDeleteAtBefore(LocalDateTime data);
    User findUserByCrypto(String crypto);
}
