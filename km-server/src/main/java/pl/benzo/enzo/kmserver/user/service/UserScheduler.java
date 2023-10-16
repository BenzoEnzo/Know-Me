package pl.benzo.enzo.kmserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public final class UserScheduler {
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 360000)
    private void deleteExpiryUsers(){
        userRepository.deleteAllByDeleteAtBefore(LocalDateTime.now());
    }
}
