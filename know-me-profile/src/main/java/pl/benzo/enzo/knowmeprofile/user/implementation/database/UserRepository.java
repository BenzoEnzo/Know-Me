package pl.benzo.enzo.knowmeprofile.user.implementation.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void deleteAllByDeleteAtBefore(LocalDateTime data);
    User findUserByCrypto(String crypto);
}
